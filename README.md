
---

###  `README.md` for `tictactoe-frontend`

```markdown
# Tic Tac Toe Frontend 🎨

This is the React frontend for the Tic Tac Toe game. It allows users to play against an AI opponent with different difficulty levels (`easy`, `medium`, `hard`). The app connects to a RESTful backend built with Spring Boot and deployed to Render.\

##NOTE: Ignore the tictactoe-backend folder as that was intially included when starting the project. To see the backend please visit backend repo listed below.

---

##  Live Demo

https://tictactoe-weld-mu.vercel.app/

---

##  Features

- Choose your AI difficulty: Easy, Medium, Hard
- Pick player symbol: `X` or `O` (Bugged and will remove)
- AI makes moves based on difficulty
- Highlights winning moves
- Tracks game outcome history (per difficulty level)
- Responsive layout

---

## 🔗 Backend API

This app communicates with:

```

[tictactoe-backend](https://github.com/FriedricNietzsche/tictactoe-backend)

````

See the [backend repo](https://github.com/<your-username>/tictactoe-backend) for API docs.

---

## 📦 Tech Stack

- React (with Hooks)
- Vite / CRA (depending on setup)
- Tailored for Vercel deployment

---

## 🚀 Getting Started

### 1. Clone the Repo

```bash
git clone https://github.com/<your-username>/tictactoe 
cd tictactoe-frontend
````

### 2. Install Dependencies

```bash
npm install
```

### 3. Configure API Endpoint

Create a `.env` file in the root:

```
REACT_APP_API_BASE=https://tictactoe-backend-j8t5.onrender.comapi/game
```

### 4. Start Local Dev Server

```bash
npm start
```

Open `http://localhost:3000`

---

##  Environment Variables (Vercel)

In [Vercel](https://vercel.com):

| Name                 | Value                                                         |
| -------------------- | ------------------------------------------------------------- |
| `REACT_APP_API_BASE` | `https://tictactoe-backend-<your-slug>.onrender.com/api/game` |

---

## 🔄 Deployment

This project is deployed using [Vercel](https://vercel.com).

Every push to `main` automatically redeploys the app.

---

## 🛠 Future Improvements

* Add multiplayer mode
* Store full match history
* UI themes or accessibility mode

---

## 📄 License

MIT — free to use, modify, and deploy.

---

## 🙌 Credits

* Game logic built in collaboration with backend AI
* Deployed on: [Render](https://render.com) & [Vercel](https://vercel.com)

```



```
