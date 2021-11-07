import {initializeApp} from "https://www.gstatic.com/firebasejs/9.0.0/firebase-app.js";
import {
    getDownloadURL,
    getStorage,
    ref,
    uploadBytes
} from "https://www.gstatic.com/firebasejs/9.0.0/firebase-storage.js";

const firebaseConfig = {
    apiKey: "AIzaSyCltMhcZmL20uUaoK9-93kGO-xJfLIdv9Y",
    authDomain: "testconnect-d6908.firebaseapp.com",
    databaseURL: "https://testconnect-d6908-default-rtdb.asia-southeast1.firebasedatabase.app",
    projectId: "testconnect-d6908",
    storageBucket: "testconnect-d6908.appspot.com",
    messagingSenderId: "813788903052",
    appId: "1:813788903052:web:1a89db230e6a0837316da9"
}


const firebaseApp = initializeApp(firebaseConfig);

const storage = getStorage(firebaseApp);

window.submitClick = function submitClick() {
    let text = document.getElementById('textarea').value;
    let out = document.getElementById('out');
    if (text === '' ) {
        text = "";
    }

    if (out === null) {
        if(text === ''){
            alert("Enter something !!")
        }else{
            $.ajax({
                type: "get",
                url: "/api/set-url",
                data: {
                    mediaUrl: "none",
                    text: text
                },
                success: function (data) {
                    var rs = document.getElementById('rs');
                    rs.insertAdjacentHTML('afterbegin', data);
                },
                error: function (request, status, error) {
                }
            });

            var t = document.getElementById('textarea')
            t.value = "";

        }


    } else {
        const file = document.getElementById('img-file').files[0];
        const file1 = document.getElementById('img-file').files[0].name;
        const img = ref(storage, 'img/' + file1);
        uploadBytes(img, file).then((snapshot) => {
            console.log('Uploaded an array!');
        });
        console.log(img);
        setTimeout(function () {
            getDownloadURL(ref(storage, 'img/' + file1))
                .then((url) => {
                    $.ajax({
                        type: "get",
                        url: "/api/set-url",
                        data: {
                            mediaUrl: url,
                            text: text
                        },
                        success: function (data) {
                            var rs = document.getElementById('rs');
                            rs.insertAdjacentHTML('afterbegin', data);
                        },
                        error: function (request, status, error) {
                        }
                    });
                })
                .catch((error) => {

                });

        }, 3000)

        setTimeout(function () {
            var t = document.getElementById('textarea')
            t.value = "";
            out.src = " ";

        }, 3000)

    }


}



