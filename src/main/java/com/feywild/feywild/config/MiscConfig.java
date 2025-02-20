package com.feywild.feywild.config;

import com.feywild.feywild.config.data.ScrollSelectType;
import org.moddingx.libx.annotation.config.RegisterConfig;
import org.moddingx.libx.config.Config;
import org.moddingx.libx.config.validate.IntRange;

@RegisterConfig("misc")
public class MiscConfig {

    @Config("Whether players should spawn with a Feywild Lexicon")
    public static boolean initial_lexicon = true;

    @Config("Whether the players should be able to select one of the court's scrolls on first login, on first time opening the guide book or none.")
    public static ScrollSelectType initial_scroll = ScrollSelectType.NONE;

    @Config("The duration in ticks for the levitation effect applied by fey dust.")
    @IntRange(min = 1)
    public static int fey_dust_ticks = 30;

    @Config("The amount of time in seconds that the magical honey needs to respawn")
    @IntRange(min = 1)
    public static int magical_honey_timer = 1200;
}
