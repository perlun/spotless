/*
 * Copyright 2022 DiffPlug
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diffplug.spotless.glue.pjf;

import com.palantir.javaformat.java.Formatter;
import com.palantir.javaformat.java.ImportOrderer;
import com.palantir.javaformat.java.JavaFormatterOptions;
import com.palantir.javaformat.java.RemoveUnusedImports;

import com.diffplug.spotless.FormatterFunc;

public class PalantirJavaFormatFormatterFunc implements FormatterFunc {

	private final Formatter formatter;

	public PalantirJavaFormatFormatterFunc() {
		formatter = Formatter.createFormatter(JavaFormatterOptions.builder()
				.style(JavaFormatterOptions.Style.PALANTIR)
				.build());
	}

	@Override
	public String apply(String input) throws Exception {
		String source = input;
		source = ImportOrderer.reorderImports(source, JavaFormatterOptions.Style.PALANTIR);
		source = RemoveUnusedImports.removeUnusedImports(source);
		return formatter.formatSource(source);
	}

	@Override
	public String toString() {
		return "PalantirJavaFormatFormatterFunc{formatter=" + formatter + '}';
	}
}
