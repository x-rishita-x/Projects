document.addEventListener("DOMContentLoaded", function () {
    let boxes = document.querySelectorAll(".box")
    let resetButton = document.querySelector("#btn")
    let newGameBtn = document.querySelector("#new-btn");
    let msgContainer = document.querySelector(".msg-container");
    let msg = document.querySelector("#msg");

    var turnO = true;//playerX,  playerO
    let count = 0;

    const winPattern = [
        [0, 1, 2],
        [0, 3, 6],
        [1, 4, 7],
        [0, 4, 8],
        [2, 5, 8],
        [2, 4, 6],
        [3, 4, 5],
        [6, 7, 8]
    ];

    boxes.forEach((box) => {
        box.addEventListener("click", () => {
            if (turnO) {
                box.innerHTML = "O";
                turnO = false;
            }
            else {
                box.innerHTML = "X";
                turnO = true;
            }
            box.disabled = true;
            count++;

            checkWinner();
        });
    });

    const checkWinner = () => {
        for (let pattern of winPattern) {
            let pos1 = boxes[pattern[0]].innerHTML;
            let pos2 = boxes[pattern[1]].innerHTML;
            let pos3 = boxes[pattern[2]].innerHTML;

            if (pos1 != "" && pos2 != "" && pos3 != "") {
                if (pos1 == pos2 && pos2 == pos3) {
                    disableBoxes();
                    console.log("winner", pos1)
                    showWinner(pos1)
                }
            }
        }
    };

    const showWinner=(winner)=>{
         msg.innerHTML=`Congratulations, Winner is ${winner}`
         msgContainer.classList.remove("hide")
    };

    const resetGame = () => {
        turnO = true;
        count = 0;
        enableBoxes();
        msgContainer.classList.add("hide");
      };

   const gameDraw = () => {
    msg.innerText = `Game was a Draw.`;
    msgContainer.classList.remove("hide");
    disableBoxes();
  };
  

   const disableBoxes = () => {
    for (let box of boxes) {
      box.disabled = true;
    }
  };
  
  const enableBoxes = () => {
    for (let box of boxes) {
      box.disabled = false;
      box.innerText = "";
    }
  };
    newGameBtn.addEventListener("click", resetGame);
resetButton.addEventListener("click", resetGame);







});


