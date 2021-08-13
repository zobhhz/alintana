const mongoose = require("mongoose");
const dotenv = require("dotenv");

dotenv.config({ path: "config.env" });

const app = require("./index");

if (process.env.MONGO_ENV === "local") {
    mongoose
        .connect(process.env.DATABASE_LOCAL, {
            useNewUrlParser: true,
            useCreateIndex: true,
            useFindAndModify: false,
            useUnifiedTopology: true,
        })
        .then(() => {
            console.log("Connection successful");
        });
} else {
    const db = process.env.DATABASE.replace("<PASSWORD>", process.env.DATABASE_PASSWORD);
    mongoose
        .connect(db, {
            useNewUrlParser: true,
            useCreateIndex: true,
            useFindAndModify: false,
            useUnifiedTopology: true,
            poolSize: 100,
        })
        .then(() => {
            console.log("Connection succesful");
        });
}

const port = process.env.PORT || 3000;

const server = app.listen(port, () => {
    console.log(`App running on port ${port}...`);
});
