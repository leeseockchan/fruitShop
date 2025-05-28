document.getElementById('itemForm').addEventListener('submit', function(e) {
            e.preventDefault();

        const item = {
        id : document.getElementById('id_id').value,
        name : document.getElementById('name_id').value,
        }

        fetch("/items/"+item.id+"/modify", {
            method : 'post',
            headers : {'Content-Type': 'application/json'},
            body : JSON.stringify(item)
        }).then(response => {
            if(response.ok){
                alert('상품이 수정 되었습니다.');
                document.getElementById('itemForm').reset();
                window.location.href = '/items';
            }else {
            alert('상품 수정에 실패하였습니다.');
            }
        }).catch(error => {
            console.error('Error : ', error);
            alert("오류가 발생하였습니다.");
        });
});