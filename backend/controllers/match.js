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
        // Increment Swipe Right by 1
        await User.findByIdAndUpdate(sender, { $inc: { dailySwipeRight: 1, allTimeSwipeRight: 1 } });

        //check if they are matched

        const recordExists = await Match.findOne({ sender: receiver, receiver: sender });
        if (recordExists) {
            // Add Matches Statistics and Experience Points for both the sender and receiver
            await User.findByIdAndUpdate(sender, { $inc: { dailyMatch: 1, allTimeMatch: 1, experience: 69 } });
            await User.findByIdAndUpdate(receiver, { $inc: { dailyMatch: 1, allTimeMatch: 1, experience: 69 } });
        }

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

exports.swipeLeft = async (req, res, next) => {
    try {
        const { sender, receiver } = req.body;

        // Increment Swipe Right by 1
        await User.findByIdAndUpdate(sender, { $inc: { dailySwipeLeft: 1, allTimeSwipeLeft: 1 } });

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
        const matchList = await Match.find({ sender });
        console.log("MATCHLIST", matchList);

        const matchedId = matchList.map((item) => item.receiver);
        const pairs = await Match.find({ sender: { $in: matchedId }, receiver: sender });
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
