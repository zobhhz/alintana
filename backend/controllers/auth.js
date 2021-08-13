const catchAsync = require("../utils/catchAsync");
const AppError = require("../utils/appError");
const User = require("../models/user");

exports.signup = catchAsync(async (req, res, next) => {
    const newUser = await User.create({
        username: req.body.username,
        sex: req.body.sex,
        birthdate: req.body.birthdate,
        mobileNumber: req.body.mobileNumber,
        location: req.body.location,
        headline: req.body.headline,
        password: req.body.password,
        confirmPassword: req.body.confirmPassword,
    });

    res.status(201).json({
        status: "success",
        data: {
            user: newUser,
        },
    });
});

exports.login = catchAsync(async (req, res, next) => {
    const { username, password } = req.body;

    // check if email and password exists
    if (!username || !password) {
        return next(new AppError("Please provide your username and password!", 400));
    }
    // check if user exists && password is correct
    const user = await User.findOne({ username }).select("+password");

    if (!user || !(await user.correctPassword(password, user.password)))
        return next(new AppError("Incorrect email or password", 401));

    user.password = undefined;
    res.status(200).json({
        status: "success",
        data: {
            user,
        },
    });
});
