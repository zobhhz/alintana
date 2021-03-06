const express = require("express");
const { getUser, addExperience, updateUser, updatePassword } = require("../controllers/user");
const router = express.Router();

//router.router("/").get().post();

router.route("/:id").get(getUser);
router.route("/").put(updateUser);
router.route("/updatePassword").put(updatePassword);

router.post("/exp/add", addExperience);

module.exports = router;
