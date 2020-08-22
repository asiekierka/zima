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

import pl.asie.zzttools.zzt.Platform;

import java.util.*;

public class ImageConverterRulesSuperZZT {
	public static final ImageConverterRuleset RULES_BLOCKS;
	public static final ImageConverterRuleset RULES_WALKABLE;
	public static final ImageConverterRuleset RULES_SAFE;
	public static final ImageConverterRuleset RULES_SAFE_STATLESS;
	public static final ImageConverterRuleset RULES_UNSAFE_STATLESS;
	public static final ImageConverterRuleset ALL_RULES;

	@SuppressWarnings({"rawtypes", "unchecked"})
	private static ImageConverterRuleset ruleset(List... lists) {
		Set<ElementRule> combinedList = new LinkedHashSet<>();
		for (List list : lists) {
			combinedList.addAll(list);
		}
		return new ImageConverterRuleset(List.copyOf(combinedList));
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private static ImageConverterRuleset rulesetSorted(List... lists) {
		Set<ElementRule> combinedList = new LinkedHashSet<>();
		for (List list : lists) {
			combinedList.addAll(list);
		}
		List<ElementRule> outputList = new ArrayList<>(combinedList);
		outputList.sort(Comparator.comparing(e -> e.getElement().getId()));
		return new ImageConverterRuleset(outputList);
	}

	static {
		List<ElementRule> unusedRules = new ArrayList<>();

		List<ElementRule> alwaysRules = new ArrayList<>();
		alwaysRules.add(ElementRule.element(Platform.SUPER_ZZT, "EMPTY"));

		List<ElementRule> blockyRules = new ArrayList<>();
		blockyRules.add(ElementRule.element(Platform.SUPER_ZZT, "SOLID"));
		blockyRules.add(ElementRule.element(Platform.SUPER_ZZT, "NORMAL"));
		blockyRules.add(ElementRule.element(Platform.SUPER_ZZT, "BREAKABLE"));
		blockyRules.add(ElementRule.element(Platform.SUPER_ZZT, "FLOOR"));
		unusedRules.add(ElementRule.element(Platform.SUPER_ZZT, "FOREST")); // (lava)
		unusedRules.add(ElementRule.element(Platform.SUPER_ZZT, "FAKE")); // (normal)

		// DrawProc-safe
		List<ElementRule> rules = new ArrayList<>();
		List<ElementRule> statlessRules = new ArrayList<>();

		rules.add(ElementRule.element(Platform.SUPER_ZZT, "LAVA"));
		statlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "LION"));
		statlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "ROTON"));
		statlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "SPIDER"));
		statlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "PAIRER"));
		statlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "TIGER"));
		statlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "CENTIPEDE_HEAD"));
		statlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "CENTIPEDE_SEGMENT"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "BULLET"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "KEY"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "AMMO"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "GEM"));
		statlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "PASSAGE"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "DOOR"));
		statlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "SCROLL"));
		statlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "RUFFIAN"));
		statlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "BEAR"));
		unusedRules.add(ElementRule.element(Platform.SUPER_ZZT, "SLIME")); // ricochet
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "BLINK_RAY_NS"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "BLINK_RAY_EW"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "RICOCHET"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "BOULDER"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "SLIDER_NS"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "SLIDER_EW"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "ENERGIZER"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "WATER_N"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "WATER_S"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "WATER_W"));
		rules.add(ElementRule.element(Platform.SUPER_ZZT, "WATER_E"));
		rules.add(ElementRule.text(Platform.SUPER_ZZT, "TEXT_BLUE", 0x1F));
		rules.add(ElementRule.text(Platform.SUPER_ZZT, "TEXT_GREEN", 0x2F));
		rules.add(ElementRule.text(Platform.SUPER_ZZT, "TEXT_CYAN", 0x3F));
		rules.add(ElementRule.text(Platform.SUPER_ZZT, "TEXT_RED", 0x4F));
		rules.add(ElementRule.text(Platform.SUPER_ZZT, "TEXT_PURPLE", 0x5F));
		rules.add(ElementRule.text(Platform.SUPER_ZZT, "TEXT_YELLOW", 0x6F));
		rules.add(ElementRule.text(Platform.SUPER_ZZT, "TEXT_WHITE", 0x0F));
		statlessRules.add(ElementRule.statP1(Platform.SUPER_ZZT, "OBJECT"));
		// uses DrawProc, but with a constant variable
		statlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "BLINK_WALL"));

		// statless; rely on how ZZT does things very specifically
		List<ElementRule> unsafeStatlessRules = new ArrayList<>();
		unsafeStatlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "OBJECT", 2));
		unsafeStatlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "DUPLICATOR", 250));
		unsafeStatlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "BOMB", 11));
		unsafeStatlessRules.add(ElementRule.element(Platform.SUPER_ZZT, "PUSHER", 31));

		// for custom rulesets
		ALL_RULES = rulesetSorted(alwaysRules, blockyRules, rules, statlessRules, unsafeStatlessRules, unusedRules);

		RULES_BLOCKS = ruleset(alwaysRules, blockyRules);
		RULES_SAFE = ruleset(alwaysRules, blockyRules, rules);
		RULES_SAFE_STATLESS = ruleset(alwaysRules, blockyRules, rules, statlessRules);
		RULES_UNSAFE_STATLESS = ruleset(alwaysRules, blockyRules, rules, statlessRules, unsafeStatlessRules);

		// extra rulesets
		RULES_WALKABLE = ruleset(alwaysRules, List.of(
				ElementRule.element(Platform.SUPER_ZZT, "FAKE"),
				ElementRule.element(Platform.SUPER_ZZT, "FLOOR")
		));
	}
}
