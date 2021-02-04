$(document).ready(function(){

    $(".sign_up").click(function (e) { 
        $("#regist").show(); 
        $(".changebg").show();       
    });

    $(".sign_in").click(function (e) {
        $("#login").show(); 
        $(".changebg").show();
    });  

    $(".close_tag").click(function (e) { 
        e.preventDefault();
        $(".changebg").hide();
        $(this).parent().hide();
        $(".add_object").removeClass("add_object_animation").show(500);
    });

    $(".add_object").click(function (e) { 
        e.preventDefault();
        $(this).addClass("add_object_animation").hide(500);
        $(".changebg").show();
        $(".item_form").show();
    });
    
    $(".user").click(function (e) { 
        window.location = "profile.jsp"
    });

    $(".buyIt").click(function (e) { 
        e.preventDefault();
        let url = "itemInfo.html?idItem="+$(this).parent().attr("id");
        window.location.href = url;
        console.log(url);
    });

    $(".editItem_button").click(function (e) { 
        e.preventDefault();
        $(".changebg").show();
        $(".editItem_form").show();
    });
	
	$("#edit_profile").click(function (e) { 
        e.preventDefault();
        $(".changebg").show();
        $(".update_userInfo").show();
    });

	$("#delete_profile").click(function (e) { 
        e.preventDefault();
        let url = "removeUser.html";
        window.location.href=url;
    });
	
	
    $(".deleteItem_button").click(function (e) { 
        e.preventDefault();
        $(".changebg").show();
        $(".deleteItemForm").show();
    });

	$(".header_img").click(function (e) { 
        e.preventDefault();
        let url = "home.jsp";
        window.location.href=url;
    });
	
	$(".chat_person").click(function (e) { 
        e.preventDefault();
        let url = "chat.html?reciver="+$(this).attr("id");
        window.location.href = url;
    });

	$(".chat").click(function (e) { 
        e.preventDefault();
        window.location.href = "allChats.jsp"
    });

});
