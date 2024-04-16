package edu.hebut.technote.response;

public class GPTSummaryResponse {
    private KeywordsDTO keywords;
    private SummaryDTO summary;

    public KeywordsDTO getKeywords() {
        return keywords;
    }

    public void setKeywords(KeywordsDTO keywords) {
        this.keywords = keywords;
    }

    public SummaryDTO getSummary() {
        return summary;
    }

    public void setSummary(SummaryDTO summary) {
        this.summary = summary;
    }

    public static class KeywordsDTO {
        private String content;
        private Object function_call;
        private String role;
        private Object tool_calls;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getFunction_call() {
            return function_call;
        }

        public void setFunction_call(Object function_call) {
            this.function_call = function_call;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public Object getTool_calls() {
            return tool_calls;
        }

        public void setTool_calls(Object tool_calls) {
            this.tool_calls = tool_calls;
        }
    }

    public static class SummaryDTO {
        private String content;
        private Object function_call;
        private String role;
        private Object tool_calls;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getFunction_call() {
            return function_call;
        }

        public void setFunction_call(Object function_call) {
            this.function_call = function_call;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public Object getTool_calls() {
            return tool_calls;
        }

        public void setTool_calls(Object tool_calls) {
            this.tool_calls = tool_calls;
        }
    }
}
