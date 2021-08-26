const User = require("../models/user");
const mongoose = require("mongoose");
exports.getUser = async (req, res, next) => {
    try {
        console.log(req.params.id);
        // const data = await User.findOne({ _id: req.params.id });

        const data = await User.findById(req.params.id);

        console.log("DATA", data);

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
