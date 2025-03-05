/*
 Navicat Premium Data Transfer

 Source Server         : MONGO_LOCAL
 Source Server Type    : MongoDB
 Source Server Version : 80004 (8.0.4)
 Source Host           : localhost:27017
 Source Schema         : microservice_customer_service

 Target Server Type    : MongoDB
 Target Server Version : 80004 (8.0.4)
 File Encoding         : 65001

 Date: 05/03/2025 08:08:11
*/


// ----------------------------
// Collection structure for customers
// ----------------------------
db.getCollection("customers").drop();
db.createCollection("customers");

// ----------------------------
// Documents of customers
// ----------------------------
db.getCollection("customers").insert([ {
    _id: ObjectId("67bc5049652bf702f7552f25"),
    customerType: "PERSONAL",
    name: "Coyaks",
    surname: "Perez",
    documentType: "DNI",
    documentNumber: "12345678",
    email: "coyaks@example.com",
    phone: "999999999",
    createdAt: ISODate("2025-02-24T10:56:09.566Z"),
    updatedAt: ISODate("2025-02-24T10:56:09.566Z"),
    _class: "com.skoy.bootcamp_microservices.model.Customer"
} ]);
db.getCollection("customers").insert([ {
    _id: ObjectId("67be562286d43e7cd15520ce"),
    customerType: "PERSONAL",
    name: "Felix",
    surname: "Perez",
    documentType: "DNI",
    documentNumber: "88888888",
    email: "felix@example.com",
    phone: "999999999",
    createdAt: ISODate("2025-02-25T23:45:38.07Z"),
    updatedAt: ISODate("2025-02-25T23:45:38.07Z"),
    _class: "com.skoy.bootcamp_microservices.model.Customer"
} ]);
db.getCollection("customers").insert([ {
    _id: ObjectId("67beb2df5c8259362896dff6"),
    customerType: "EMPRESARIAL",
    name: "INNOVACODERS SOLUCIONES TECNOLOGICAS",
    surname: "",
    documentType: "RUC",
    documentNumber: "20612226599",
    email: "innovacoders@example.com",
    phone: "999999999",
    createdAt: ISODate("2025-02-26T06:21:19.568Z"),
    updatedAt: ISODate("2025-02-26T06:21:19.568Z"),
    _class: "com.skoy.bootcamp_microservices.model.Customer"
} ]);
db.getCollection("customers").insert([ {
    _id: ObjectId("67c80f32d082587cc57705a6"),
    customerType: "PERSONAL_VIP",
    name: "Felix",
    surname: "Perez",
    documentType: "DNI",
    documentNumber: "78999999",
    email: "felix@example.com",
    phone: "999999999",
    createdAt: ISODate("2025-03-05T08:45:38.064Z"),
    updatedAt: ISODate("2025-03-05T08:45:38.064Z"),
    _class: "com.skoy.bootcamp_microservices.model.Customer"
} ]);
db.getCollection("customers").insert([ {
    _id: ObjectId("67c8191ad082587cc57705a7"),
    customerType: "EMPRESARIAL_PYME",
    name: "INNOVACODERS PYME",
    surname: "",
    documentType: "RUC",
    documentNumber: "20612226599",
    email: "innovacoders@example.com",
    phone: "999999999",
    createdAt: ISODate("2025-03-05T09:27:54.335Z"),
    updatedAt: ISODate("2025-03-05T09:27:54.335Z"),
    _class: "com.skoy.bootcamp_microservices.model.Customer"
} ]);
db.getCollection("customers").insert([ {
    _id: ObjectId("67c82a25d082587cc57705a8"),
    customerType: "PERSONAL",
    name: "CoyaksV2",
    surname: "Perez",
    documentType: "DNI",
    documentNumber: "88888888",
    email: "coyaksv2@example.com",
    phone: "999999999",
    createdAt: ISODate("2025-03-05T10:40:37.594Z"),
    updatedAt: ISODate("2025-03-05T10:40:37.594Z"),
    _class: "com.skoy.bootcamp_microservices.model.Customer"
} ]);
db.getCollection("customers").insert([ {
    _id: ObjectId("67c83654d082587cc57705ab"),
    customerType: "PERSONAL",
    name: "CoyaksV3",
    surname: "Perez",
    documentType: "DNI",
    documentNumber: "88888888",
    email: "coyaksv2@example.com",
    phone: "999999999",
    createdAt: ISODate("2025-03-05T11:32:36.682Z"),
    updatedAt: ISODate("2025-03-05T11:32:36.682Z"),
    _class: "com.skoy.bootcamp_microservices.model.Customer"
} ]);
