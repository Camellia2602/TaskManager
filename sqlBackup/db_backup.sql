/*
Navicat PGSQL Data Transfer

Source Server         : dd
Source Server Version : 90405
Source Host           : localhost:5432
Source Database       : manager
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90405
File Encoding         : 65001

Date: 2015-11-18 22:07:37
*/


-- ----------------------------
-- Sequence structure for t_priority_f_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_priority_f_id_seq";
CREATE SEQUENCE "public"."t_priority_f_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 10
 CACHE 1;
SELECT setval('"public"."t_priority_f_id_seq"', 10, true);

-- ----------------------------
-- Sequence structure for t_task_f_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_task_f_id_seq";
CREATE SEQUENCE "public"."t_task_f_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 10
 CACHE 1;
SELECT setval('"public"."t_task_f_id_seq"', 10, true);

-- ----------------------------
-- Table structure for t_priority
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_priority";
CREATE TABLE "public"."t_priority" (
"f_id" int8 DEFAULT nextval('t_priority_f_id_seq'::regclass) NOT NULL,
"f_name" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of t_priority
-- ----------------------------
INSERT INTO "public"."t_priority" VALUES ('1', 'Idle');
INSERT INTO "public"."t_priority" VALUES ('2', 'Below normal');
INSERT INTO "public"."t_priority" VALUES ('3', 'Normal');
INSERT INTO "public"."t_priority" VALUES ('4', 'Above normal');
INSERT INTO "public"."t_priority" VALUES ('5', 'High');

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_task";
CREATE TABLE "public"."t_task" (
"f_id" int8 DEFAULT nextval('t_task_f_id_seq'::regclass) NOT NULL,
"f_description" varchar(1000) COLLATE "default",
"f_name" varchar(50) COLLATE "default",
"f_priorityid" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of t_task
-- ----------------------------
INSERT INTO "public"."t_task" VALUES ('1', 'desc1', 'test1', '1');
INSERT INTO "public"."t_task" VALUES ('2', 'desc2', 'test2', '2');
INSERT INTO "public"."t_task" VALUES ('3', 'desc3', 'test3', '3');
INSERT INTO "public"."t_task" VALUES ('4', 'desc4', 'test4', '4');
INSERT INTO "public"."t_task" VALUES ('5', 'desc5', 'test5', '5');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "public"."t_priority_f_id_seq" OWNED BY "t_priority"."f_id";
ALTER SEQUENCE "public"."t_task_f_id_seq" OWNED BY "t_task"."f_id";

-- ----------------------------
-- Primary Key structure for table t_priority
-- ----------------------------
ALTER TABLE "public"."t_priority" ADD PRIMARY KEY ("f_id");

-- ----------------------------
-- Primary Key structure for table t_task
-- ----------------------------
ALTER TABLE "public"."t_task" ADD PRIMARY KEY ("f_id");
