/**
 * Copyright (c) 2020 Adrian Siekierka
 *
 * This file is part of zima.
 *
 * zima is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * zima is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with zima.  If not, see <http://www.gnu.org/licenses/>.
 */
package pl.asie.zzttools.zima;

import lombok.*;
import pl.asie.zzttools.zzt.Element;
import pl.asie.zzttools.zzt.Platform;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class ElementRule {
	public enum Strategy {
		EMPTY(false),
		ELEMENT(false),
		TEXT(false),
		USE_STAT_P1(true);

		private final boolean requiresStat;

		Strategy(boolean requiresStat) {
			this.requiresStat = requiresStat;
		}

		public boolean isRequiresStat() {
			return requiresStat;
		}
	}

	private final Element element;
	private final Strategy strategy;
	private final int chr;
	private final int color;

	public static ElementRule element(Platform platform, String name) {
		Element element = platform.getLibrary().byInternalName(name);
		if (element == null) {
			throw new RuntimeException();
		}
		return element(platform, name, element.getCharacter());
	}

	public static ElementRule element(Platform platform, String name, int chr) {
		Element element = platform.getLibrary().byInternalName(name);
		if (element == null) {
			throw new RuntimeException();
		}
		return new ElementRule(element, element.getId() == 0 ? Strategy.EMPTY : Strategy.ELEMENT, chr, -1);
	}

	public static ElementRule text(Platform platform, String name, int color) {
		Element element = platform.getLibrary().byInternalName(name);
		if (element == null) {
			throw new RuntimeException();
		}
		return new ElementRule(element, Strategy.TEXT, -1, color);
	}

	public static ElementRule statP1(Platform platform, String name) {
		Element element = platform.getLibrary().byInternalName(name);
		if (element == null) {
			throw new RuntimeException();
		}
		return new ElementRule(element, Strategy.USE_STAT_P1, -1, -1);
	}
}
