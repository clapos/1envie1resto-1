function validate()
{
	var result = true;
	for(var i= 0; i<table.length; i++)
	{
		if(table[i].value.length == 0)
		{
			result = false;
		}
	}
	submit = document.querySelector('#inscription input[type="submit"]');
	if(result)
	{
		submit.removeAttribute("disabled");
	}
	else
	{
		submit.setAttribute("disabled", "disabled");
	}
}

function init()
{
	table = document.querySelectorAll('#inscription input[type="text"],#inscription input[type="password"]');
	for(var i = 0; i < table.length; i++)
	{
		table[i].addEventListener('change', validate, false);
	}
}
window.addEventListener('load', init);