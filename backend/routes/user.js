const express = require("express");

const { getUser, addExperience } = require("../controllers/user");
const router = express.Router();

//router.router("/").get().post();

router.get("/:id", getUser);

router.post("/exp/add", addExperience);

module.exports = router;
