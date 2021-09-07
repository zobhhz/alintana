const User = require("../models/user");
const mongoose = require("mongoose");
const { cloudinary } = require("../utils/cloudinary");

exports.getUser = async (req, res, next) => {
    try {
        const data = await User.findById(req.params.id);
        if (data == null) throw "No User";

        res.status(200).json(data);
    } catch (err) {
        console.log(err);
        res.status(500).json({
            status: "Error",
            message: "Error",
        });
    }
};

exports.updateUser = async (req, res, next) => {
    try {
        const splitDate = req.body.birthdate.split("/");

        let update = {
            username: req.body.username,
            sex: req.body.sex,
            birthdate: new Date(Date.UTC(parseInt(splitDate[2]), parseInt(splitDate[0] - 1), parseInt(splitDate[1]))),
            mobileNumber: req.body.mobileNumber,
            location: req.body.location,
            headline: req.body.headline,
            preference: req.body.preference,
        };
        var uploadStr = "data:image/jpeg;base64," + req.body.fileStr;
        const uploadedResponse = await cloudinary.uploader.upload(uploadStr, {
            upload_preset: "ml_default",
        });

        update.userimg = uploadedResponse.url;
        console.log(req.body.id);
        const user = await User.findById(req.body.id);
        console.log(user);

        const data = await User.findByIdAndUpdate(req.body.id, update);

        res.status(200).json({
            status: "Success",
            data,
        });
    } catch (err) {
        console.log(err);
        res.status(500).json({
            status: "Error",
            message: "Error",
        });
    }
};

exports.addExperience = async (req, res, next) => {
    try {
        const { id, value } = req.body;

        const data = await User.findByIdAndUpdate(id, { $inc: { experience: value } });
        res.status(200).json(data);
    } catch (err) {
        res.status(500).json({
            status: "Error",
            message: "Error",
        });
    }
};
