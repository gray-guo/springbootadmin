
base on spring boot3 and mybatis plus

include drools rule engine

DDL
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for rules
-- ----------------------------
DROP TABLE IF EXISTS `rules`;
CREATE TABLE `rules`  (
`id` int(11) NOT NULL AUTO_INCREMENT,
`key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
`condition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
`value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
`group_id` int(11) NULL DEFAULT NULL,
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rules
-- ----------------------------
INSERT INTO `rules` VALUES (1, 'age', '==', '25', 1);
INSERT INTO `rules` VALUES (2, 'weight', '>=', '100', 1);
INSERT INTO `rules` VALUES (3, 'age', '==', '30', 2);
INSERT INTO `rules` VALUES (4, 'weight', '<=', '100', 2);

SET FOREIGN_KEY_CHECKS = 1;

