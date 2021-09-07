const mongoose = require("mongoose");
const validator = require("validator");
const bcrypt = require("bcrypt");

const userSchema = new mongoose.Schema({
    username: {
        type: String,
        required: [true, "name field is required"],
        minlength: 4,
        unique: true,
    },
    birthdate: {
        type: Date,
        required: [true, "birthdate field is required"],
    },
    sex: {
        type: String,
        enum: ["Prefer Not To Say", "Male", "Female", "Non-binary", "Gay", "Lesbian", "Other"],
        default: "Prefer Not To Say",
    },
    mobileNumber: {
        type: String,
    },
    location: {
        type: String,
    },
    headline: {
        type: String,
    },
    password: {
        type: String,
        required: [true, "provide a password"],
        minlength: 8,
        select: false,
    },
    confirmPassword: {
        type: String,
        required: [true, "Please confirm your password"],
    },
    experience: {
        type: Number,
        default: 0,
    },
    createdAt: {
        type: Date,
        default: Date.now(),
    },
    userimg: {
        type: String,
        default: "",
    },
    preference: {
        type: String,
        default: "Any",
    },
});

userSchema.methods.correctPassword = async function (candidatePassword, userPassword) {
    return await bcrypt.compare(candidatePassword, userPassword);
};
userSchema.pre("save", async function (next) {
    // run this function if password was modified
    if (!this.isModified("password")) return next();

    // Hash the password with cost 12
    this.password = await bcrypt.hash(this.password, 12);

    // delete password confirm field
    this.confirmPassword = undefined;
    next();
});

userSchema.pre(/^find/, function (next) {
    // this points to the current query
    this.select;
    next();
});

const User = mongoose.model("User", userSchema);

module.exports = User;
