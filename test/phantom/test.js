/*global console:true, phantom:true, WebPage:true */

var page = new WebPage();
page.onConsoleMessage = function(msg) {
  console.log(msg);
};

page.onLoadFinished = function() {
  page.injectJs("js/test.js");
  phantom.exit();
};

page.open("about:blank");
