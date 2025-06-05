package net.harrison.battleroyalezone.config;

public class ZoneConfig {

    private static final int tick = 20;

    private static final int INI_ZONE_SIZE = 600;

    private static final int[] ZONE_SIZES = {
            300,
            150,
            70,
            30,
            5
    };

    private static final int[] ZONE_WARNING_TICKS = {
            50 * tick,
            40 * tick,
            40 * tick,
            30 * tick,
            20 * tick
    };

    private static final int[] ZONE_SHRINK_TICKS = {
            90 * tick,
            75 * tick,
            60 * tick,
            45 * tick,
            30 * tick
    };


    public static int getIniZoneSize() {
        return INI_ZONE_SIZE;
    }

    public static int getZoneSize(int stage) {
        return ZONE_SIZES[stage];
    }

    public static int getWarningTick(int stage) {
        return ZONE_WARNING_TICKS[stage];
    }

    public static int getShrinkTick(int stage) {
        return ZONE_SHRINK_TICKS[stage];
    }

    public static int getMaxStage() {
        return ZONE_SIZES.length;
    }

    public static String getInfo() {
        StringBuilder string = new StringBuilder();

        string.append("§6大逃杀缩圈系统配置:\n");

        string.append("§7-初始圈大小:§e").append(INI_ZONE_SIZE).append("方块\n\n");

        for (int i = 0; i < ZONE_SIZES.length; i++) {
            string.append("§7- 第").append(i+1).append("圈大小: §e")
                    .append(ZONE_SIZES[i]).append("方块\n");
        }
        string.append("\n");
        for (int i = 0; i < ZONE_WARNING_TICKS.length; i++) {
            string.append("§7- 第").append(i+1).append("圈警告时间: §e")
                    .append(ZONE_WARNING_TICKS[i] / tick).append("秒\n");
        }
        string.append("\n");
        for (int i = 0; i < ZONE_SHRINK_TICKS.length; i++) {
            string.append("§7- 第").append(i+1).append("圈大小: §e")
                    .append(ZONE_SHRINK_TICKS[i] / tick).append("秒\n");
        }

        return string.toString();

    }


}
