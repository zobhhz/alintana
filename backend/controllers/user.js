const User = require("../models/user");

exports.getUser = async (req, res, next) => {
    try {
        const data = await User.findById(req.params.id);
        console.log(data);
        res.status(200).json(data);
    } catch (err) {
        res.status(500).json({
            status: "Error",
            message: "Error",
        });
    }
};
