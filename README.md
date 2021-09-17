# alintana

MOBDEVE Machine Project

## Backend Backup

1. if ever the fetching of data from the default url which is `https://alintana-backend.herokuapp.com` is slow. Uncomment out the alternative url
   found at `alintana/app/src/main/java/com/mobdeve/s17/dizon/palmares/alintana/api/APIClient.kt` line `11` and comment line `10`

2. Then open a terminal of the folder and traverse to `backend` directory `cd backend`

3. run `npm install` to install the dependencies

4. Create a `config.env` file (inside backend directory) and input the needed environment variables found below

5. run `npm run start` to start the backend

### Config.env variables

      PORT=3000
      DATABASE=mongodb+srv://alyssa:\<PASSWORD>@cluster0.llilw.mongodb.net/alintana?retryWrites=true&w=majority
      DATABASE_LOCAL=mongodb://localhost:27017/alintana
      DATABASE_PASSWORD=Oc0wcxoiFr2oyZm4
      CLOUDINARY_NAME = db29tm4pj
      CLOUDINARY_API_KEY = 927632498667314
      CLOUDINARY_API_SECRET = t2LCmMLBvS7RYdLGhhtSvBYLbDE
