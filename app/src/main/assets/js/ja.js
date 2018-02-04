function textToSpeech(s){
	Android.showToast(s);
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
			var score = 0;
			inputs = document.getElementsByTagName("input");
			for (a of inputs){
				arr = a.value.toLowerCase().split(" ");
				s = "";
				for (b of arr){
					if (b!=""){
						s += b + " ";
					}
				}
				if (s.trim() == a.getAttribute("right_answer") || s.trim() == a.getAttribute("right_answer2")){
					score++;			
					a.setAttribute("class","right");
				}
				else{
					a.setAttribute("class","wrong");
				}
			}
			//a.length
			alert(score);
		});
		
		$(".refresh").click(function(){
			var score = 0;
			inputs = document.getElementsByTagName("input");
			for (a of inputs){
				a.value = "";
				a.setAttribute("size","5");
			}
		});
	})