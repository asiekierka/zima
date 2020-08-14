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

import pl.asie.zzttools.Constants;
import pl.asie.zzttools.util.FileUtils;
import pl.asie.zzttools.zima.gui.ZimaFrontendSwing;
import pl.asie.zzttools.zzt.TextVisualData;

import java.util.Objects;

public class ImageConverterMain {
	public static final String VERSION = "0.1.0";

	public static void main(String[] args) throws Exception {
		TextVisualData visual = new TextVisualData(8, 14,
				FileUtils.readAll(Objects.requireNonNull(ImageConverterMain.class.getClassLoader().getResourceAsStream("8x14.bin"))),
				Constants.EGA_PALETTE);

		ZimaFrontendSwing frontend = new ZimaFrontendSwing(visual, VERSION);
	}
}
