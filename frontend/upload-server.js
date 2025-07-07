import express from 'express'
import multer from 'multer'
import path from 'path'
import cors from 'cors'
import { fileURLToPath } from 'url'
import { dirname } from 'path'

const __filename = fileURLToPath(import.meta.url)
const __dirname = dirname(__filename)

const app = express()
app.use(cors())
app.use(express.static(path.join(__dirname, 'public')))

const storage = multer.diskStorage({
    destination: path.join(__dirname, 'public/uploads'),
    filename: (req, file, cb) => {
        cb(null, file.originalname)
    }
})

const upload = multer({ storage })

app.post('/upload', upload.single('file'), (req, res) => {
    res.status(200).json({ message: 'Файл сохранён' })
})

app.listen(3001, () => {
    console.log('Upload server running on http://localhost:3001')
})
