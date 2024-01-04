//list of all mouse events : https://developer.mozilla.org/en-US/docs/Web/API/Element#mouse_events
let resume_button= document.getElementById("btn-1")

// the first argument is the event which we want to listen and 
//the second argument is the function we want to operate when the event happens

resume_button.addEventListener("click", ()=>{
    // say if we wanted to change the html part of the code to 
    //something else then the code would have had been

    /*
    document.querySelector(".box").innerHTML=
    "<b>
        "any statement "
    </b>"
    */ 
    window.location.href = 'https://www.overleaf.com/latex/templates/deedy-cv/bjryvfsjdyxz';

})

let github_button=document.getElementById("btn-2")

github_button.addEventListener("click", ()=>{
    window.location.href="https://github.com/x-rishita-x/"
})

let submit_button=document.getElementById("submit-btn")

submit_button.addEventListener("click", ()=>{

})

