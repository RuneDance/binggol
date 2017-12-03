var testEditor;
$(document).ready(function () {
    testEditor = editormd("test-editormd", {
        width: "90%",
        height: 640,
        syncScrolling: "single",
        path: "../plugs/editor.md-1.5.0/lib/",
        theme : "dark",
        previewTheme : "dark",
        editorTheme : "pastel-on-dark"
    });
});