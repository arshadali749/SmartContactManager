
//this is the function fired when the delete contact button is pressed
function deleteContact(id)
	{
		swal({
			  title: "Do you really want to delete this contact?",
			  text: "Once deleted, you will not be able to recover this contact",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
			    window.location="/contacts/delete/"+id;
			      
			    }
			  else {
			    swal("Your contact is safe!");
			  }
			});
	}
	
