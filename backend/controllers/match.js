const Match = require("../models/match");
const User = require("../models/user");

exports.getPossibleMatches = async (req, res, next) => {
    try {
        const matchList = await Match.find({ sender: req.params.id });

        const matchedId = matchList.map((item) => item.receiver);

        const matches = await User.find({ _id: { $nin: [req.params.id, ...matchedId] } });

        res.status(200).json({
            status: "Success",
            length: matches.length,
            data: matches,
        });
    } catch (err) {
        res.status(500).json({
            status: "Fail",
            message: "Unkown Error",
        });
    }
};

exports.addMatch = async (req, res, next) => {
    try {
        const { sender, receiver } = req.body;

        const data = await Match.create({
            sender,
            receiver,
        });

        res.status(201).json({
            status: "Success",
            data,
        });
    } catch (err) {
        res.status(500).json({
            status: "Fail",
            message: "Unkown Error",
        });
    }
};

exports.getMatched = async (req, res, next) => {
    try {
        const sender = req.params.id;

        const matchList = await Match.find({ sender: req.params.id });

        const matchedId = matchList.map((item) => item.receiver);

        const pairs = await Match.find({ sender: { $in: matchedId }, receiver: req.params.id });

        const pairsId = pairs.map((item) => item.sender);

        const data = await User.find({ _id: { $in: [...pairsId] } });

        res.status(200).json({
            status: "Success",
            data,
        });
    } catch (err) {
        res.status(500).json({
            status: "Fail",
            message: "Unknown Error",
        });
    }
};
