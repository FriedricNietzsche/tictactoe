import { useState } from "react";

function App() {
  const [sessionId, setSessionId] = useState(null);
  const [board, setBoard] = useState([
    ["_", "_", "_"],
    ["_", "_", "_"],
    ["_", "_", "_"]
  ]);
  const [status, setStatus] = useState("");
  const [playerSymbol, setPlayerSymbol] = useState("X");
  const [aiLevel, setAiLevel] = useState("easy");
  const [gameStarted, setGameStarted] = useState(false);
  const [gameHistory, setGameHistory] = useState({ easy: [], medium: [], hard: [] });
  const [winningLine, setWinningLine] = useState([]);
  const [currentTurn, setCurrentTurn] = useState("X");

  const apiBase = process.env.REACT_APP_API_BASE || "http://localhost:8080/api/game";

  const newGame = async () => {
    console.log("Start Game clicked");
    const res = await fetch(
      `${apiBase}/new?aiLevel=${aiLevel}&playerSymbol=${playerSymbol}`,
      { method: "GET" }
    );
    const data = await res.json();
    setSessionId(data.sessionId);
    console.log("Session ID:", data.sessionId);
    setGameStarted(true);
    console.log("Game started");
    setWinningLine([]);
    setBoard([
      ["_", "_", "_"],
      ["_", "_", "_"],
      ["_", "_", "_"]
    ]);
    setStatus("");
  };

  const fetchBoardState = async (id) => {
    const res = await fetch(`${apiBase}/state?sessionId=${id}`);
    const data = await res.json();
    if (Array.isArray(data.board)) setBoard(data.board);
    if (data.winningLine) setWinningLine(data.winningLine);
    if (data.winner && data.winner !== "1") {
      setStatus(`${data.winner} wins!`);
      setGameHistory(prev => ({
        ...prev,
        [aiLevel]: [...prev[aiLevel], `${data.winner} wins`]
      }));
    } else if (data.draw) {
      setStatus("Draw!");
      setGameHistory(prev => ({
        ...prev,
        [aiLevel]: [...prev[aiLevel], "Draw"]
      }));
    } else {
      setStatus("");
    }
  };

  const makeMove = async (row, col) => {
    if (!sessionId || status) return;
    const res = await fetch(
      `${apiBase}/move?sessionId=${sessionId}&row=${row}&col=${col}`,
      { method: "POST" }
    );
    const data = await res.json();
    if (data.error) {
      alert(data.error);
      return;
    }
    if (Array.isArray(data.board)) setBoard(data.board);
    if (data.winningLine) setWinningLine(data.winningLine);
    if (data.winner) {
      setStatus(`${data.winner} wins!`);
      setGameHistory(prev => ({
        ...prev,
        [aiLevel]: [...prev[aiLevel], `${data.winner} wins`]
      }));
    } else if (data.draw) {
      setStatus("Draw!");
      setGameHistory(prev => ({
        ...prev,
        [aiLevel]: [...prev[aiLevel], "Draw"]
      }));
    }
  };

  const isWinningCell = (i, j) => {
    return winningLine.some(([x, y]) => x === i && y === j);
  };

  return (
    <div style={{ backgroundColor: "#1a1a1a", color: "white", minHeight: "100vh", fontFamily: "Arial, sans-serif" }}>
      <div style={{ maxWidth: "500px", margin: "0 auto", padding: "1rem" }}>
        <h1 style={{ fontSize: "2rem", fontWeight: "bold" }}>Tic Tac Toe</h1>

        {!gameStarted && (
          <div style={{ marginTop: "2rem" }}>
            <label>AI Level:</label>
            <select value={aiLevel} onChange={(e) => setAiLevel(e.target.value)}>
              <option value="easy">Easy</option>
              <option value="medium">Medium</option>
              <option value="hard">Hard</option>
            </select>
            <br /><br />
            <label>Player Symbol:</label>
            <select
              value={playerSymbol}
              onChange={(e) => setPlayerSymbol(e.target.value)}
            >
              <option value="X">X</option>
              <option value="O">O</option>
            </select>
            <br /><br />
            <button onClick={newGame} style={{ padding: "10px 20px", backgroundColor: "#444", color: "white", border: "none", borderRadius: "4px", cursor: "pointer" }}>Start Game</button>

            <div style={{ marginTop: "2rem" }}>
              <h3>Game History</h3>
              {Object.entries(gameHistory).map(([level, history]) => (
                <div key={level} style={{ marginBottom: "1rem" }}>
                  <strong>{level.charAt(0).toUpperCase() + level.slice(1)}:</strong>
                  <ul>
                    {history.map((entry, idx) => (
                      <li key={idx}>{entry}</li>
                    ))}
                  </ul>
                </div>
              ))}
            </div>
          </div>
        )}

        {gameStarted && board.length === 3 && (
          <div>
            <button onClick={() => setGameStarted(false)} style={{ marginBottom: "1rem", backgroundColor: "#444", color: "white", border: "none", borderRadius: "4px", padding: "5px 10px", cursor: "pointer" }}>⬅ Back</button>
            <div
              style={{
                display: "grid",
                gridTemplateColumns: "repeat(3, 70px)",
                gap: "8px",
                marginBottom: "1rem"
              }}
            >
              {board.map((row, i) =>
                row.map((cell, j) => (
                  <button
                    key={`${i}-${j}`}
                    onClick={() => {
                    if (currentTurn === playerSymbol && !status) {
                      makeMove(i, j);
                    }
                  }}
                    style={{
                      width: "70px",
                      height: "70px",
                      fontSize: "1.5rem",
                      fontWeight: "bold",
                      backgroundColor: isWinningCell(i, j) ? "#90ee90" : "#333",
                      color: "white",
                      border: "1px solid #666",
                      borderRadius: "6px",
                      cursor: "pointer"
                    }}
                  >
                    {cell === "_" ? "" : cell}
                  </button>
                ))
              )}
            </div>
            <p style={{ fontWeight: "bold", fontSize: "1.2rem" }}>{status}</p>
            <button onClick={newGame} style={{ padding: "8px 16px", backgroundColor: "#444", color: "white", border: "none", borderRadius: "4px", cursor: "pointer" }}>Restart Game</button>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
