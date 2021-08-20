const catchAsync = require("../utils/catchAsync");
const AppError = require("../utils/appError");
const User = require("../models/user");

exports.signup = catchAsync(async (req, res, next) => {
    const splitDate = req.body.birthdate.split("/");
    const newUser = await User.create({
        username: req.body.username,
        sex: req.body.sex,
        birthdate: new Date(Date.UTC(parseInt(splitDate[2]), parseInt(splitDate[0] - 1), parseInt(splitDate[1]))),
        mobileNumber: req.body.mobileNumber,
        location: req.body.location,
        headline: req.body.headline,
        password: req.body.password,
        confirmPassword: req.body.confirmPassword,
    });

    res.status(201).json({
        status: "success",
        data: newUser,
    });
});

exports.login = catchAsync(async (req, res, next) => {
    console.log(req.body);
    const { username, password } = req.body;

    // check if email and password exists
    if (!username || !password) {
        return next(new AppError("Please provide your username and password!", 400));
    }
    // check if user exists && password is correct
    const user = await User.findOne({ username }).select("+password");

    if (!user || !(await user.correctPassword(password, user.password))) {
        res.status(401).json({
            status: "error",
            message: "Incorrect Email or Password",
            data: {},
        });
    } else {
        user.password = undefined;
        res.status(200).json({
            status: "success",
            message: "You have successfully congratulations!",
            data: user,
        });
    }
});
