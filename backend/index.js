const path = require("path");
const express = require("express");
const morgan = require("morgan");
const dotenv = require("dotenv");
const userRoutes = require("./routes/user");
const authRoutes = require("./routes/auth");

dotenv.config({ path: "config.env" });

const app = express();

if (process.env.NODE_ENV === "production") app.use(enforce.HTTPS({ trustProtoHeader: true }));

app.use(morgan("dev"));

app.use(express.json({ limit: "10kb" }));

app.use("/", authRoutes);
app.use("/api/v1/users", userRoutes);

module.exports = app;
