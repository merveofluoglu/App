<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image to Base64</title>
</head>
<body>
<input type="file" id="inputImage" accept="image/*">
<textarea id="outputBase64" rows="10" cols="80" readonly></textarea>

<script>
    document.getElementById('inputImage').addEventListener('change', function(event) {
        const file = event.target.files[0];
        const reader = new FileReader();

        reader.onloadend = function() {
            const base64 = reader.result;
            document.getElementById('outputBase64').value = base64;
        };

        reader.readAsDataURL(file);
    });
</script>
</body>
</html>