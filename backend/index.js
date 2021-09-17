const path = require("path");
const express = require("express");
const morgan = require("morgan");
const dotenv = require("dotenv");
const Agenda = require("agenda");
const userRoutes = require("./routes/user");
const authRoutes = require("./routes/auth");
const matchRoutes = require("./routes/match");
const quizRoutes = require("./routes/quiz");
const User = require("./models/user");

dotenv.config({ path: "config.env" });

const app = express();

//if (process.env.NODE_ENV === "production") app.use(enforce.HTTPS({ trustProtoHeader: true }));

app.use(morgan("dev"));

app.use(express.json({ limit: "50mb" }));

// DAILY TASK
const db = process.env.DATABASE.replace(
  "<PASSWORD>",
  process.env.DATABASE_PASSWORD
);

const agenda = new Agenda({ db: { address: db } });

agenda.define("update daily tasks", async (job) => {
  await User.updateMany(
    {},
    { dailySwipeRight: 0, dailySwipeLeft: 0, dailyGame: 0, dailyMatch: 0 }
  );
});

(async function () {
  await agenda.start();
  await agenda.every("0 4 * * 0-6", "update daily tasks");
})();

app.use("/", authRoutes);
app.use("/api/v1/user", userRoutes);
app.use("/api/v1/match", matchRoutes);
app.use("/api/v1/quiz", quizRoutes);
module.exports = app;
