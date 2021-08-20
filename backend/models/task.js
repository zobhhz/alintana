const mongoose = require("mongoose");

const taskSchema = mongoose.Schema({
    date: {
        type: Date,
        default: Date.now(),
    },
    description: {
        type: String,
    },
    isComplete: {
        type: Boolean,
    },
    type: {
        type: Number,
    },
});

const Task = mongoose.model("Task", taskSchema);

module.exports = Task;
