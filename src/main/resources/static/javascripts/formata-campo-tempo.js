var Brewer = Brewer || {};

Brewer.MaskTime = (function() {
	
	function MaskTime() {
		this.time = $('.js-time');
		
	}
	
	MaskTime.prototype.enable = function() {
//		this.decimal.maskMoney({ decimal: ',', thousands: '.' });
//		this.plain.maskMoney({ precision: 0, thousands: '.' });
		this.time.maskNumber({ decimal: '"', thousands: '.' });
		
	}
	
	return MaskTime;
	
}());

$(function() {
	var maskTime = new Brewer.MaskTime();
	maskTime.enable();
	
	
	
});