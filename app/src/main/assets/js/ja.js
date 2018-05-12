function updateScoreToDatabase(currentScoreString, maxScore){
	AndroidExercise.updateScoreIntoDatabase(currentScoreString, maxScore);
}

$(function(){		
		$("input").focus(function(){
			$(this).attr("class","normal");
		});		
		$("input").focusout(function(){
			if ($(this).val().length > 0){
				$(this).attr('size', $(this).val().length / 2);
			}
			else{
				$(this).attr('size',5);
			}
		});
		
		$(".submit").click(function(){
		
			totalScore = 0;
			scoreString = "";
			countQuestion = 0;
			//Show answer
			answer = document.getElementById('answer')
			answer.setAttribute('class','answers')
			exercises = document.getElementsByClassName("exercise");
			for (var i=0; i< exercises.length; i++){
				exercise = exercises[i];
				scoreExercise = 0;
				inputs = exercise.getElementsByTagName("input");
				for (var j=0; j< inputs.length; j++){
					input = inputs[j];
					countQuestion++;
					arr = input.value.toLowerCase().split(" ");
					s = "";
					for (var k=0; k< arr.length; k++){
						b = arr[k];
						if (b!=""){
							s += b + " ";
						}
					}
					if (s.trim() == input.getAttribute("right_answer") || s.trim() == input.getAttribute("right_answer2")){
						totalScore++;	
						scoreExercise++;
						input.setAttribute("class","right");
					}
					else{
						input.setAttribute("class","wrong");
					}
				}
				scoreString += scoreExercise + " ";
			}
			updateScoreToDatabase(scoreString.trim(), countQuestion);
		});
		
		$(".refresh").click(function(){
			answer = document.getElementById('answer')
			answer.setAttribute('class','answers_hide')
			var score = 0;
			inputs = document.getElementsByTagName("input");
			for (var i=0; i< inputs.length; i++){
				a = inputs[i];
				a.value = "";
				a.setAttribute("size","5");
			}
		});
	})