/*
 * @ {#} SkillLevel.java   1.0     12/13/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.week05_nguyentanthaiduong_21049641.backend.enums;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/13/2024
 * @version:    1.0
 */
public enum SkillLevel {
    BEGINNER(1),
    INTERMEDIATE(2),
    ADVANCED(3),
    PROFESSIONAL(4),
    MASTER(5);

    private final byte level;

    SkillLevel(int level) {
        this.level = (byte) level;
    }

    public byte getLevel() {
        return level;
    }

    // Dùng để chuyển từ byte sang SkillLevel
    public static SkillLevel fromByte(byte level) {
        for (SkillLevel skillLevel : SkillLevel.values()) {
            if (skillLevel.getLevel() == level) {
                return skillLevel;
            }
        }
        throw new IllegalArgumentException("Unknown level: " + level);
    }
}

