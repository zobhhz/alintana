const fs = require("fs");
const mongoose = require("mongoose");
const dotenv = require("dotenv");
const path = require("path");
const User = require("./../models/user");
const Matches = require("./../models/match");
const Quiz =  require("./../models/quiz");

dotenv.config({ path: path.resolve(__dirname, "../config.env") });

if (process.env.MONGO_ENV === "local") {
    console.log("IMPORTING LOCALLY");
    mongoose
        .connect(process.env.DATABASE_LOCAL, {
            useNewUrlParser: true,
            useCreateIndex: true,
            useFindAndModify: false,
        })
        .then(() => {
            console.log("Connection successful");
        });
} else {
    console.log("IMPORTING AT MONGODB ATLAS");
    const db = process.env.DATABASE.replace("<PASSWORD>", process.env.DATABASE_PASSWORD);

    mongoose
        .connect(db, {
            useNewUrlParser: true,
            useCreateIndex: true,
            useFindAndModify: false,
        })
        .then(() => {
            console.log("Connection succesful");
        });
}

//READ JSON File

const users = JSON.parse(fs.readFileSync(`${__dirname}/users.json`, "utf-8"));
const quiz = JSON.parse(fs.readFileSync(`${__dirname}/quiz.json`, "utf-8"));

// IMPORT DATA INTO DB
const importData = async () => {
    try {
        await User.create(users, { validateBeforeSave: false });
        await Quiz.create(quiz);
        console.log("Data successfully loaded!");
    } catch (err) {
        console.log(err);
    }
    process.exit();
};

// DELETE ALL DATA FROM COLLECTION

const deleteData = async () => {
    try {
        await User.deleteMany();
        await Matches.deleteMany();
        await Quiz.deleteMany();
        console.log("Data successfully deleted");
    } catch (err) {
        console.log(err);
    }
    process.exit();
};

if (process.argv[2] === "--import") importData();
if (process.argv[2] === "--delete") deleteData();

console.log(process.argv);
