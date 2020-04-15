/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : recommendationsystem

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 15/04/2020 14:34:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for rt_acomments
-- ----------------------------
DROP TABLE IF EXISTS `rt_acomments`;
CREATE TABLE `rt_acomments`  (
  `cmId` int(11) NOT NULL AUTO_INCREMENT,
  `cmContent` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `aId` int(11) NOT NULL,
  `author` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cmEditorDate` datetime(0) NOT NULL,
  PRIMARY KEY (`cmId`) USING BTREE,
  INDEX `aId`(`aId`) USING BTREE,
  INDEX `author`(`author`) USING BTREE,
  CONSTRAINT `rt_aComments_ibfk_1` FOREIGN KEY (`aId`) REFERENCES `rt_articles` (`aId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rt_aComments_ibfk_2` FOREIGN KEY (`author`) REFERENCES `rt_users` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rt_acomments
-- ----------------------------
INSERT INTO `rt_acomments` VALUES (1, '文章评论', 8, 'test02', '2020-01-08 17:33:48');
INSERT INTO `rt_acomments` VALUES (2, 'okokokokokokok', 8, 'test02', '2020-01-08 17:34:02');
INSERT INTO `rt_acomments` VALUES (4, '部署成功', 11, 'test01', '2020-01-09 14:12:10');
INSERT INTO `rt_acomments` VALUES (5, '文章保存出现失败，原因，图片添加时采用win+shift+s截图粘贴添加，导致后台无法获取图片的文件名。', 15, 'test01', '2020-02-06 15:14:34');
INSERT INTO `rt_acomments` VALUES (7, '添加redis后，效果不明显，有待改进', 15, 'test02', '2020-02-07 14:17:54');
INSERT INTO `rt_acomments` VALUES (8, '火狐浏览器下文章添加失败，正文无法保存。', 15, 'test01', '2020-02-24 17:00:04');
INSERT INTO `rt_acomments` VALUES (11, '指数测试', 21, 'test02', '2020-03-12 21:49:15');

-- ----------------------------
-- Table structure for rt_articles
-- ----------------------------
DROP TABLE IF EXISTS `rt_articles`;
CREATE TABLE `rt_articles`  (
  `aId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(26) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cId` int(11) NOT NULL,
  `contentPath` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `coverPhotoPath` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `coverPhotoUrl` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `digest` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `editorDate` datetime(0) NOT NULL,
  `identification` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `bIds` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`aId`) USING BTREE,
  INDEX `rt_articles_rt_classification_cId_fk`(`cId`) USING BTREE,
  CONSTRAINT `rt_articles_rt_classification_cId_fk` FOREIGN KEY (`cId`) REFERENCES `rt_classification` (`cId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rt_articles
-- ----------------------------
INSERT INTO `rt_articles` VALUES (3, '第十届矛盾文学奖获奖作品如何吸人眼球', 2, 'E:/projects/ideaProjects/recommendation/articles/test0120191225205553.html', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendation/imgs/articleCoverImgs/test0120191225205553.jpg', 'http://localhost:8081/recommendation/imgs/articleCoverImgs/test0120191225205553.jpg', '本书是作家陈彦在第十届茅盾文学奖的获奖作品', '2019-12-25 21:04:00', 'test0120191225205553', NULL);
INSERT INTO `rt_articles` VALUES (8, '第三篇文章测试', 1, 'E:/projects/ideaProjects/recommendation/articles/test0120191227023158.html', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendation/imgs/articleCoverImgs/test0120191227023158.jpg', 'http://localhost:8081/recommendation/imgs/articleCoverImgs/test0120191227023158.jpg', '2okokokokokok not3', '2019-12-27 02:32:21', 'test0120191227023158', NULL);
INSERT INTO `rt_articles` VALUES (9, '第一篇文章测试', 1, 'E:/projects/ideaProjects/recommendation/articles/test0120200105152023.html', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendation/imgs/articleCoverImgs/test0120200105152023.jpg', 'http://localhost:8081/recommendation/imgs/articleCoverImgs/test0120200105152023.jpg', '第一篇文章测试，这是简介简介简介', '2020-01-05 15:21:46', 'test0120200105152023', NULL);
INSERT INTO `rt_articles` VALUES (10, '文章测试', 16, 'E:/projects/ideaProjects/recommendation/articles/test0120200105152209.html', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendation/imgs/articleCoverImgs/test0120200105152209.jpg', 'http://localhost:8081/recommendation/imgs/articleCoverImgs/test0120200105152209.jpg', '内容简介，字数有要求。', '2020-01-05 15:24:03', 'test0120200105152209', NULL);
INSERT INTO `rt_articles` VALUES (11, '何为真正的自我，一本关于摆脱期待的书', 2, 'E:/projects/ideaProjects/recommendation/articles/test0120200108220731.html', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendation/imgs/articleCoverImgs/test0120200108220731.jpg', 'http://localhost:8081/recommendation/imgs/articleCoverImgs/test0120200108220731.jpg', '《无声告白》是伍绮诗耗时六年写就的第一本长篇小说，故事编排精妙细致，文笔沉稳内敛，一经出版便广受好评，成为2014年度最具实力且众望所归的黑马，获得2014年亚马逊年度图书第一名', '2020-01-08 22:14:14', 'test0120200108220731', NULL);
INSERT INTO `rt_articles` VALUES (15, ' 关于系统介绍与系统测试的记录，以及优化记录。', 19, 'E:/projects/ideaProjects/recommendation/articles/test0120200206150052.html', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendation/imgs/articleCoverImgs/test0120200206150052.jpg', 'http://localhost:8081/recommendation/imgs/articleCoverImgs/test0120200206150052.jpg', ' 关于系统介绍与系统测试的记录，以及优化记录。', '2020-02-06 15:03:03', 'test0120200206150052', NULL);
INSERT INTO `rt_articles` VALUES (21, '人间有草木', 4, 'E:/projects/ideaProjects/recommendation/articles/test0120200224170027.html', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendation/imgs/articleCoverImgs/test0120200224170027.jpg', 'http://localhost:8081/recommendation/imgs/articleCoverImgs/test0120200224170027.jpg', '当代著名小说家、散文家汪曾祺经典选集。', '2020-02-24 17:04:10', 'test0120200224170027', NULL);
INSERT INTO `rt_articles` VALUES (22, '我们是天空，我们不是大地', 18, 'E:/projects/ideaProjects/recommendation/articles/test0120200224171515.html', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendation/imgs/articleCoverImgs/test0120200224171515.jpg', 'http://localhost:8081/recommendation/imgs/articleCoverImgs/test0120200224171515.jpg', '著名记者阿列克谢耶维奇冒着核辐射危险，深入事故发生现场，历时数年，访问了上百位受到核灾影响的平民。作者将这些访谈以口述的方式书写，每一页呈现的都是残酷、荒诞的故事。从口述者的独白中透露出，这场灾难造成的痛苦始终如核辐射般残存在幸存者的体内。', '2020-02-24 17:23:44', 'test0120200224171515', NULL);
INSERT INTO `rt_articles` VALUES (23, '火狐浏览器测试', 1, 'E:/projects/ideaProjects/recommendation/articles/test0120200224234736.html', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendation/imgs/articleCoverImgs/test0120200224234736.png', 'http://localhost:8081/recommendation/imgs/articleCoverImgs/test0120200224234736.png', '火狐浏览器测试，测试正文保存，未做任何的修改。这是简介', '2020-02-24 23:48:44', 'test0120200224234736', NULL);

-- ----------------------------
-- Table structure for rt_articlesindex
-- ----------------------------
DROP TABLE IF EXISTS `rt_articlesindex`;
CREATE TABLE `rt_articlesindex`  (
  `aiId` int(11) NOT NULL AUTO_INCREMENT,
  `aId` int(11) NOT NULL,
  `arHits` int(11) NULL DEFAULT 0,
  `arBrowseIndex` int(11) NULL DEFAULT 0,
  `arIndex` float NULL DEFAULT 0,
  `arComments` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`aiId`) USING BTREE,
  UNIQUE INDEX `aId`(`aId`) USING BTREE,
  CONSTRAINT `rt_articlesindex_ibfk_1` FOREIGN KEY (`aId`) REFERENCES `rt_articles` (`aId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rt_articlesindex
-- ----------------------------
INSERT INTO `rt_articlesindex` VALUES (1, 3, 1, 0, 0, 0);
INSERT INTO `rt_articlesindex` VALUES (2, 8, 0, 0, 0, 0);
INSERT INTO `rt_articlesindex` VALUES (3, 9, 0, 0, 0, 0);
INSERT INTO `rt_articlesindex` VALUES (4, 10, 0, 0, 0, 0);
INSERT INTO `rt_articlesindex` VALUES (5, 11, 1, 1, 50.5, 0);
INSERT INTO `rt_articlesindex` VALUES (6, 15, 0, 0, 0, 0);
INSERT INTO `rt_articlesindex` VALUES (7, 21, 15, 2, 32.75, 1);
INSERT INTO `rt_articlesindex` VALUES (8, 22, 1, 0, 0, 0);
INSERT INTO `rt_articlesindex` VALUES (9, 23, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for rt_bcomments
-- ----------------------------
DROP TABLE IF EXISTS `rt_bcomments`;
CREATE TABLE `rt_bcomments`  (
  `cmId` int(11) NOT NULL AUTO_INCREMENT,
  `cmContent` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `bId` int(11) NOT NULL,
  `author` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cmEditorDate` datetime(0) NOT NULL,
  PRIMARY KEY (`cmId`) USING BTREE,
  INDEX `bId`(`bId`) USING BTREE,
  INDEX `author`(`author`) USING BTREE,
  CONSTRAINT `rt_bComments_ibfk_1` FOREIGN KEY (`bId`) REFERENCES `rt_books` (`bId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rt_bComments_ibfk_2` FOREIGN KEY (`author`) REFERENCES `rt_users` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rt_bcomments
-- ----------------------------
INSERT INTO `rt_bcomments` VALUES (1, '图书评论测试，我与地坛', 1, 'test02', '2020-01-08 16:56:32');

-- ----------------------------
-- Table structure for rt_books
-- ----------------------------
DROP TABLE IF EXISTS `rt_books`;
CREATE TABLE `rt_books`  (
  `bId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cId` int(11) NULL DEFAULT NULL,
  `publication` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `publicationDate` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `coverPhotoPath` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `coverPhotoUrl` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `digest` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `viewed` int(11) NULL DEFAULT NULL,
  `whoAdd` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`bId`) USING BTREE,
  INDEX `rt_books_rt_classification_cId_fk`(`cId`) USING BTREE,
  CONSTRAINT `rt_books_rt_classification_cId_fk` FOREIGN KEY (`cId`) REFERENCES `rt_classification` (`cId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rt_books
-- ----------------------------
INSERT INTO `rt_books` VALUES (1, '我与地坛', '史铁生', 4, '人民文学出版社', '2011-01-01', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/585246843785426.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/585246843785426.jpg', '是史铁生文学作品中，充满哲思又极为人性化的代表作之一。', NULL, NULL);
INSERT INTO `rt_books` VALUES (2, '西游记', '吴承恩', 3, '', '', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/596892396389344.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/596892396389344.jpg', '', NULL, NULL);
INSERT INTO `rt_books` VALUES (11, '美好的青春校园生活', '揭战', 9, '广东科技学院', '2011-01-1', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/20191219230943.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/20191219230943.jpg', '朱伟兴66666666666666666666666666', NULL, NULL);
INSERT INTO `rt_books` VALUES (12, '美好的青春校园生活', '揭战', 9, '人民文学出版社', '2011-01-1', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/20191219231531.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/20191219231531.jpg', '朱伟兴666666666', NULL, NULL);
INSERT INTO `rt_books` VALUES (16, '2343', '324324', 3, '23434', '24432', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/201912200131450.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/201912200131450.jpg', '32432', NULL, NULL);
INSERT INTO `rt_books` VALUES (17, '2343ewt', '324324tre', 3, '23434', '24432', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/201912200137380.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/201912200137380.jpg', '32432', NULL, NULL);
INSERT INTO `rt_books` VALUES (19, '此生未完成', '于娟', 4, '湖南文艺出版社', '2019-08', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/201912200145440.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/201912200145440.jpg', '一部关于生命与生活思索的书。记录了作者癌症治疗过程的经历、对人生的理解、对自身的反思等。', NULL, NULL);
INSERT INTO `rt_books` VALUES (20, '一禅小和尚', '一禅小和尚', 7, '江苏凤凰文艺出版社', '2017-09', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/201912200145441.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/201912200145441.jpg', '这世间一定存在着许多美好的，足以过滤尘埃，这就是《一禅小和尚》的由来。', NULL, NULL);
INSERT INTO `rt_books` VALUES (21, '万历十五年', '黄仁宇', 8, '中华书局', '2016-01', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/201912200150460.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/201912200150460.jpg', '这是黄仁宇的名作《万历十五年》的最新中文版。', NULL, NULL);
INSERT INTO `rt_books` VALUES (22, '梦幻花', '东野圭吾', 10, '北京联合出版社公司', '2019-12', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/201912200156250.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/201912200156250.jpg', '错综交织的悬案，延续百年的谜团。伴随着黄色牵牛花的重现世间，四个陌生家庭的命运被紧紧联系在一起。', NULL, NULL);
INSERT INTO `rt_books` VALUES (24, '这里是中国', '中国青藏高原研究会', 11, '中信出版社', '2019-09', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/20191225203238.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/20191225203238.jpg', '本书集结了星球研究所近三年来中国主题文章的精华。全书收录了300余张精致摄影作品，涵盖诗词、动植物、人文等等丰富内容，以广阔的地理视觉和宏大的时间尺度，重新解读中国故事。', NULL, NULL);
INSERT INTO `rt_books` VALUES (25, '红楼梦', '曹雪芹', 3, '', '', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/201912252051240.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/201912252051240.jpg', '中国四大名著之一', NULL, NULL);
INSERT INTO `rt_books` VALUES (27, '软件工程导论', '', 5, '清华大学出版社', '', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/201912252051242.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/201912252051242.jpg', '', NULL, NULL);
INSERT INTO `rt_books` VALUES (28, '主角', '陈彦', 2, '作家出版社', '2018-01', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/201912252051243.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/201912252051243.jpg', '一部动人心魄的书。丰富复杂的故事情节，鲜活生动的人物群像，方言口语的巧妙使用。。。。', NULL, NULL);
INSERT INTO `rt_books` VALUES (29, '我们为什么会生病', '伦道夫·M.尼斯', 12, '湖南科学技术出版社', '2018-03', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/20191230124415.png', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/20191230124415.png', '演化理论是人类理解自然规律的重大突破。人是演化的产物，人类健康的方方面面自然也遵循演化规律。主流生物医学探究疾病的生理过程、分子机制，进而对症下药、精准治疗。在本书中，作者主张从演化的视角审视人体、疾病、衰老等健康议题，别开生面，旁征博引，启发思考，对广大普通读者及专业人士都不无裨益。', NULL, NULL);
INSERT INTO `rt_books` VALUES (30, '测试1', 'test01', 19, 'no', '2020-01-06', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/202001061656180.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/202001061656180.jpg', 'wuuwuwuuwuwuw\n', NULL, NULL);
INSERT INTO `rt_books` VALUES (31, '测试2', 'test01', 19, 'no', '2020-01-06', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/202001061656181.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/202001061656181.jpg', 'wuuwuwuuwuwuw\n', NULL, NULL);
INSERT INTO `rt_books` VALUES (32, '测试3', 'test01', 19, 'no', '2020-01-06', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/202001061656182.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/202001061656182.jpg', 'wuuwuwuuwuwuw\n', NULL, NULL);
INSERT INTO `rt_books` VALUES (33, '测试4', 'test01', 19, 'no', '2020-01-06', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/202001061656183.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/202001061656183.jpg', 'wuuwuwuuwuwuw\n', NULL, NULL);
INSERT INTO `rt_books` VALUES (34, '测试5', 'test01', 19, 'no', '2020-01-06', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/202001061656184.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/202001061656184.jpg', 'wuuwuwuuwuwuw\n', NULL, NULL);
INSERT INTO `rt_books` VALUES (35, '测试6', 'test01', 19, 'no', '2020-01-06', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/202001061656185.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/202001061656185.jpg', 'wuuwuwuuwuwuw\n', NULL, NULL);
INSERT INTO `rt_books` VALUES (36, '测试7长书名测试长书名测试', 'test01', 19, 'no', '2020-01-06', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/202001061656186.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/202001061656186.jpg', 'wuuwuwuuwuwuw\n', NULL, NULL);
INSERT INTO `rt_books` VALUES (37, '测试8', 'test01', 19, 'no', '2020-01-06', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/202001061658550.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/202001061658550.jpg', 'kykykykykykykk', NULL, NULL);
INSERT INTO `rt_books` VALUES (38, '测试9', 'test01', 19, 'no', '2020-01-06', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/202001061658551.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/202001061658551.jpg', 'kykykykykykykk', NULL, NULL);
INSERT INTO `rt_books` VALUES (39, '测试10', 'test01', 19, 'no', '2020-01-06', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/202001061658552.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/202001061658552.jpg', 'kykykykykykykk', NULL, NULL);
INSERT INTO `rt_books` VALUES (40, '无声告白', '伍绮诗', 2, '江苏凤凰文艺出版社', '2015-08-01', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/20200108215235.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/20200108215235.jpg', '我们终此一身，就是要摆脱他人的期待，找到真正的自己。《无声告白》是伍绮诗耗时六年写就的第一本长篇小说，故事编排精妙细致，文笔沉稳内敛，一经出版便广受好评，成为2014年度最具实力且众望所归的黑马。', NULL, NULL);
INSERT INTO `rt_books` VALUES (46, '挪威的森林', '村上春树，林少华', 2, '上海译文出版社', '2018-03', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/20200108220657.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/20200108220657.jpg', '挪威的森林》是日本作家村上春树所著的一部长篇爱情小说，影响了几代读者的青春名作。故事讲述主角渡边纠缠在情绪不稳定且患有精神疾病的直子和开朗活泼的小林绿子之间，苦闷彷徨，最终展开了自我救赎和成长的旅程。', NULL, NULL);
INSERT INTO `rt_books` VALUES (47, '人间草木', '汪曾祺', 4, '湖南少年儿童出版社', '2019-04', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/20200224161433.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/20200224161433.jpg', '当代著名小说家、散文家汪曾祺经典选集，多篇入选教育部新编语文教材！这是一本适合青少年阅读的汪曾祺先生作品集。全书共分为四卷，分别是：人间草木、美食美味、故人时事、品味人生。', NULL, NULL);
INSERT INTO `rt_books` VALUES (48, '切尔诺贝利的祭祷', '斯维特兰娜·阿列克谢耶维奇', 18, '中信出版社', '2018-08', 'D:/1/2/apache-tomcat-9.0.22-test/projects/recommendationrecommendation/imgs/bookCoverImgs/20200224171328.jpg', 'http://localhost:8081/recommendation/imgs/bookCoverImgs/20200224171328.jpg', '', NULL, NULL);

-- ----------------------------
-- Table structure for rt_classicsayings
-- ----------------------------
DROP TABLE IF EXISTS `rt_classicsayings`;
CREATE TABLE `rt_classicsayings`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `provenance` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rt_classicsayings
-- ----------------------------
INSERT INTO `rt_classicsayings` VALUES (1, '我们终此一身，就是要摆脱他人的期待，找到真正的自己。', '无声告白');

-- ----------------------------
-- Table structure for rt_classification
-- ----------------------------
DROP TABLE IF EXISTS `rt_classification`;
CREATE TABLE `rt_classification`  (
  `cId` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `addDate` date NULL DEFAULT NULL,
  `symbolImgUrl` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cId`) USING BTREE,
  UNIQUE INDEX `value`(`value`) USING BTREE,
  UNIQUE INDEX `value_2`(`value`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rt_classification
-- ----------------------------
INSERT INTO `rt_classification` VALUES (1, '豆瓣8.0+', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/1.jpg');
INSERT INTO `rt_classification` VALUES (2, '小说', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/2.jpg');
INSERT INTO `rt_classification` VALUES (3, '名著', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/3.jpg');
INSERT INTO `rt_classification` VALUES (4, '诗歌散文', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/4.jpg');
INSERT INTO `rt_classification` VALUES (5, '青春', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/5.jpg');
INSERT INTO `rt_classification` VALUES (6, '教辅经典', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/6.JPG');
INSERT INTO `rt_classification` VALUES (7, '漫画绘本', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/7.jpg');
INSERT INTO `rt_classification` VALUES (8, '历史', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/8.jpg');
INSERT INTO `rt_classification` VALUES (9, '心理', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/9.jpg');
INSERT INTO `rt_classification` VALUES (10, '推理', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/10.jpg');
INSERT INTO `rt_classification` VALUES (11, '科普', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/11.jpg');
INSERT INTO `rt_classification` VALUES (12, '社科', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/12.jpg');
INSERT INTO `rt_classification` VALUES (13, '外文原著', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/13.jpg');
INSERT INTO `rt_classification` VALUES (14, '生活', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/14.jpg');
INSERT INTO `rt_classification` VALUES (15, '文学', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/15.jpg');
INSERT INTO `rt_classification` VALUES (16, '艺术', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/16.jpg');
INSERT INTO `rt_classification` VALUES (17, '影视原著', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/17.jpg');
INSERT INTO `rt_classification` VALUES (18, '传记', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/18.JPG');
INSERT INTO `rt_classification` VALUES (19, '技能提升', '2019-12-13', 'https://wrtcloud.cn/images/recommendation/classificationImages/19.jpg');

-- ----------------------------
-- Table structure for rt_rearticles
-- ----------------------------
DROP TABLE IF EXISTS `rt_rearticles`;
CREATE TABLE `rt_rearticles`  (
  `id` int(11) NOT NULL,
  `aId` int(11) NULL DEFAULT NULL,
  `reIndex` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `aId`(`aId`) USING BTREE,
  CONSTRAINT `rt_reArticles_ibfk_1` FOREIGN KEY (`aId`) REFERENCES `rt_articles` (`aId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rt_rearticles
-- ----------------------------
INSERT INTO `rt_rearticles` VALUES (1, 11, 1);
INSERT INTO `rt_rearticles` VALUES (2, 23, 2);
INSERT INTO `rt_rearticles` VALUES (3, 22, 3);
INSERT INTO `rt_rearticles` VALUES (4, 21, 4);
INSERT INTO `rt_rearticles` VALUES (5, 15, 5);

-- ----------------------------
-- Table structure for rt_retologinusers
-- ----------------------------
DROP TABLE IF EXISTS `rt_retologinusers`;
CREATE TABLE `rt_retologinusers`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reIndex` int(11) NULL DEFAULT NULL,
  `reAid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  INDEX `reAid`(`reAid`) USING BTREE,
  CONSTRAINT `rt_retologinusers_ibfk_1` FOREIGN KEY (`username`) REFERENCES `rt_users` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rt_retologinusers_ibfk_2` FOREIGN KEY (`reAid`) REFERENCES `rt_articles` (`aId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rt_retologinusers
-- ----------------------------
INSERT INTO `rt_retologinusers` VALUES (1, 'test01', 1, 23);
INSERT INTO `rt_retologinusers` VALUES (2, 'test01', 2, 11);
INSERT INTO `rt_retologinusers` VALUES (3, 'test01', 3, 22);
INSERT INTO `rt_retologinusers` VALUES (4, 'test01', 4, 8);
INSERT INTO `rt_retologinusers` VALUES (5, 'test01', 5, 21);
INSERT INTO `rt_retologinusers` VALUES (6, 'test02', 1, 21);
INSERT INTO `rt_retologinusers` VALUES (7, 'test02', 2, 11);
INSERT INTO `rt_retologinusers` VALUES (8, 'test02', 3, 23);
INSERT INTO `rt_retologinusers` VALUES (9, 'test02', 4, 8);
INSERT INTO `rt_retologinusers` VALUES (10, 'test02', 5, 15);

-- ----------------------------
-- Table structure for rt_test
-- ----------------------------
DROP TABLE IF EXISTS `rt_test`;
CREATE TABLE `rt_test`  (
  `t1` int(11) NULL DEFAULT NULL,
  `t2` int(11) NULL DEFAULT NULL,
  `t3` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rt_test
-- ----------------------------
INSERT INTO `rt_test` VALUES (32, 12, 44);
INSERT INTO `rt_test` VALUES (2, 5, 6);

-- ----------------------------
-- Table structure for rt_tusers
-- ----------------------------
DROP TABLE IF EXISTS `rt_tusers`;
CREATE TABLE `rt_tusers`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tcode` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `validity` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rt_tusers
-- ----------------------------
INSERT INTO `rt_tusers` VALUES (1, 'test01', 'w1285528700@163.com', '605818', '106814476');
INSERT INTO `rt_tusers` VALUES (3, 'test02', '1285528700@qq.com', '763352', '174263991');
INSERT INTO `rt_tusers` VALUES (4, 'test02', '185528700@qq.com', '008076', '174236265');

-- ----------------------------
-- Table structure for rt_userdesk
-- ----------------------------
DROP TABLE IF EXISTS `rt_userdesk`;
CREATE TABLE `rt_userdesk`  (
  `user` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `bId` int(11) NOT NULL,
  INDEX `user`(`user`) USING BTREE,
  INDEX `bId`(`bId`) USING BTREE,
  CONSTRAINT `rt_userDesk_ibfk_1` FOREIGN KEY (`user`) REFERENCES `rt_users` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rt_userDesk_ibfk_2` FOREIGN KEY (`bId`) REFERENCES `rt_books` (`bId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rt_userdesk
-- ----------------------------
INSERT INTO `rt_userdesk` VALUES ('test01', 48);
INSERT INTO `rt_userdesk` VALUES ('test02', 47);
INSERT INTO `rt_userdesk` VALUES ('test02', 48);
INSERT INTO `rt_userdesk` VALUES ('test02', 40);
INSERT INTO `rt_userdesk` VALUES ('test02', 46);
INSERT INTO `rt_userdesk` VALUES ('test02', 29);
INSERT INTO `rt_userdesk` VALUES ('test02', 28);
INSERT INTO `rt_userdesk` VALUES ('test02', 20);

-- ----------------------------
-- Table structure for rt_userhabit
-- ----------------------------
DROP TABLE IF EXISTS `rt_userhabit`;
CREATE TABLE `rt_userhabit`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cId` int(11) NOT NULL,
  `cIndex` float NULL DEFAULT 0,
  `cBookIndex` int(11) NULL DEFAULT 0,
  `cBrowseIndex` int(11) NULL DEFAULT 0,
  `cHits` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  CONSTRAINT `rt_userhabit_ibfk_1` FOREIGN KEY (`username`) REFERENCES `rt_users` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rt_userhabit
-- ----------------------------
INSERT INTO `rt_userhabit` VALUES (1, 'test02', 1, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (2, 'test02', 2, 61, 3, 0, 1);
INSERT INTO `rt_userhabit` VALUES (3, 'test02', 3, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (4, 'test02', 4, 34, 1, 2, 4);
INSERT INTO `rt_userhabit` VALUES (5, 'test02', 5, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (6, 'test02', 6, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (7, 'test02', 7, 0, 1, 0, 0);
INSERT INTO `rt_userhabit` VALUES (8, 'test02', 8, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (9, 'test02', 9, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (10, 'test02', 10, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (11, 'test02', 11, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (12, 'test02', 12, 0, 1, 0, 0);
INSERT INTO `rt_userhabit` VALUES (13, 'test02', 13, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (14, 'test02', 14, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (15, 'test02', 15, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (16, 'test02', 16, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (17, 'test02', 17, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (18, 'test02', 18, 0, 1, 1, 1);
INSERT INTO `rt_userhabit` VALUES (19, 'test02', 19, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (20, 'test01', 1, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (21, 'test01', 2, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (22, 'test01', 3, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (23, 'test01', 4, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (24, 'test01', 5, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (25, 'test01', 6, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (26, 'test01', 7, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (27, 'test01', 8, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (28, 'test01', 9, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (29, 'test01', 10, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (30, 'test01', 11, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (31, 'test01', 12, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (32, 'test01', 13, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (33, 'test01', 14, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (34, 'test01', 15, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (35, 'test01', 16, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (36, 'test01', 17, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (37, 'test01', 18, 0, 0, 0, 0);
INSERT INTO `rt_userhabit` VALUES (38, 'test01', 19, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for rt_users
-- ----------------------------
DROP TABLE IF EXISTS `rt_users`;
CREATE TABLE `rt_users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rt_users
-- ----------------------------
INSERT INTO `rt_users` VALUES (1, 'test01', 'w1285528700@163.com', 'CF814721358D09942B255746542AD2A4');
INSERT INTO `rt_users` VALUES (3, 'test02', '1285528700@qq.com', 'CF814721358D09942B255746542AD2A4');

-- ----------------------------
-- Triggers structure for table rt_articles
-- ----------------------------
DROP TRIGGER IF EXISTS `addAidToArIndex`;
delimiter ;;
CREATE TRIGGER `addAidToArIndex` AFTER INSERT ON `rt_articles` FOR EACH ROW begin
    declare a int;
    select aId into a from rt_articles order by aId desc limit 0, 1;
    insert into rt_articlesIndex(aId) values(a);
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table rt_articlesindex
-- ----------------------------
DROP TRIGGER IF EXISTS `updateArIndex`;
delimiter ;;
CREATE TRIGGER `updateArIndex` BEFORE UPDATE ON `rt_articlesindex` FOR EACH ROW begin
   set new.arIndex = ((new.arBrowseIndex+100)/(new.arHits+1))*new.arBrowseIndex + new.arComments*20;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table rt_test
-- ----------------------------
DROP TRIGGER IF EXISTS `tri`;
delimiter ;;
CREATE TRIGGER `tri` BEFORE UPDATE ON `rt_test` FOR EACH ROW begin
    set new.t3 = new.t2 + new.t1;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table rt_userhabit
-- ----------------------------
DROP TRIGGER IF EXISTS `autoUpdateUserHabit`;
delimiter ;;
CREATE TRIGGER `autoUpdateUserHabit` BEFORE UPDATE ON `rt_userhabit` FOR EACH ROW begin
        set new.cIndex = new.cBookIndex*20 + new.cBrowseIndex * 5 + new.cHits *1;
    end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table rt_users
-- ----------------------------
DROP TRIGGER IF EXISTS `autoAddToUHR`;
delimiter ;;
CREATE TRIGGER `autoAddToUHR` AFTER INSERT ON `rt_users` FOR EACH ROW begin
    declare a int;
    declare b int;
    declare c int;
    declare u varchar(16);
    set a = 1, b=1;
    select username into u from rt_users order by id desc limit 0,1;
    while a < 20 do
            insert into rt_userHabit(username, cId) values(u,a);
            set a = a +1;
        end while;
    while b < 6 do
        select aId into c from rt_rearticles where reIndex = b;
        insert into rt_reToLoginUsers(username,reIndex,reAid) values(username,b,c);
        set b = b + 1;
        end while;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
