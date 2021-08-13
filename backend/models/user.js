const mongoose = require("mongoose");
const validator = require("validator");
const bcrypt = require("bcrypt");

const userSchema = new mongoose.Schema({
    username: {
        type: String,
        required: [true, "name field is required"],
        minlength: 7,
        unique: true,
    },
    birthdate: {
        type: Date,
        required: [true, "birthdate field is required"],
    },
    sex: {
        type: String,
        enum: ["Prefer Not To Say", "Male", "Female", "Non-binary"],
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
    },
    confirmPassword: {
        type: String,
        required: [true, "Please confirm your password"],
    },
    experience: {
        type: Number,
        default: 0,
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
    this.passwordConfirm = undefined;
    next();
});

const User = mongoose.model("User", userSchema);

module.exports = User;
