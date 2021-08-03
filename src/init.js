import "./db";
import "./models/Menu";
import app from "./server";

const PORT = 5000;

const handleListening = () =>
  console.log(`✅ Server listenting on http://localhost:${PORT} 🚀`);

app.listen(PORT, handleListening);
