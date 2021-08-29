const User = require("../models/user");
const mongoose = require("mongoose");
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
