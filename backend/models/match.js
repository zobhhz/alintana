const mongoose = require("mongoose");

const matchSchema = mongoose.Schema({
    sender: {
        type: mongoose.Schema.ObjectId,
        ref: "User",
        required: [true, "A match should have a sender"],
    },
    receiver: {
        type: mongoose.Schema.ObjectId,
        ref: "User",
        required: [true, "A match should have a receiver"],
    },
});

matchSchema.index({ sender: 1, receiver: 1 }, { unique: true });

const Match = mongoose.model("Match", matchSchema);

module.exports = Match;
