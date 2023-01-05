<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>객체 탐지</title>
</head>
<body style="margin: 40px">
	<h3>객체 탐지 결과</h3>
	<hr />
	<canvas id="tcanvas" width="100" height="100"></canvas>

	<script>
		let jsonStr = '${jsonresult}';
		let obj = JSON.parse(jsonStr);
		let predictions = obj.predictions[0];
		let num = parseInt(predictions.num_detections);
		let names = predictions.detection_names;
		let scores = predictions.detection_scores;
		let boxes = predictions.detection_boxes;

		const canvas = document.getElementById('tcanvas');
		let ctx = canvas.getContext('2d');
		let img = new Image();

		img.src = '/sTemp/02/${fileName}';
		img.onload = function() {
			canvas.width = img.width;
			canvas.height = img.height;
			ctx.drawImage(img, 0, 0, img.width, img.height);

			ctx.strokeStyle = 'red';
			ctx.linewidth = 10;
			ctx.fillStyle = 'red';
			ctx.font = 'bold 12px Arial';
			ctx.textBaseline = 'bottom';

			for (let i = 0; i < num; i++) {
				let x = boxes[i][1] * img.width;
				let y = boxes[i][0] * img.height;
				let w = (boxes[i][3] - boxes[i][1]) * img.width;
				let h = (boxes[i][2] - boxes[i][0]) * img.height;
				let label = names[i] + ' (' + parseInt(scores[i] * 100) + '%)';
				ctx.strokeRect(x, y, w, h);
				ctx.fillText(label, x, y);
			}
		};
	</script>
</body>
</html>
