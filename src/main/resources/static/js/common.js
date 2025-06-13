export const inputValidator = {
    hasWhiteSpace: (str) => /\s/.test(str),
    hasSpecialChar: (str) =>/[!@#$%^&*():{}|<>,'~_=\.\-\\`]/.test(str),
    startWithNumber: (str) => /^[0-9]/.test(str),
    // 결과를 반대로 리턴
    validateLandLineNumber: (str) => {
        if (!str || str.trim() === '') return false;
        const withHyphen = /^(?:\d{2,3}-)?\d{3,4}-\d{4}$/;
        const withoutHyphen = /^(?:\d{9,11})$/;
        return !(withHyphen.test(str) || withoutHyphen.test(str));
    },
    // 결과를 반대로 리턴
    validateMobileNumber: (str) => {
        if (!str || str.trim() === '') return false;
        const mobileRegex = /^01([0|1|6|7|8|9])(\d{3,4})(\d{4})$/;
        const mobileHyphenRegex = /^01([0|1|6|7|8|9])-?(\d{3,4})-?(\d{4})$/;
        return !(mobileRegex.test(str) || mobileHyphenRegex.test(str));
    },
    // 결과를 반대로 리턴
    validateBusinessNumber: (str) => {
        if (!str || str.trim() === '') return false;
       const withHyphen = /^\d{3}-\d{2}-\d{5}$/;
       const withoutHyphen = /^\d{10}$/;
       return !(withHyphen.test(str) || withoutHyphen.test(str));
    }
}