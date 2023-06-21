import Cookies from 'js-cookie'

// 配置自己的 token 参数
const uToken = 'u_token'
const darkMode = 'dark_mode'

export function getToken(){
    return Cookies.get(uToken);
}

export function setToken(token){
    return Cookies.set(uToken, token, {expires: 1}) // 超时时间1天
}

export function removeToken(){
    return Cookies.remove(uToken)
}

export function removeAll(){
    return Cookies.Cookies.removeAll()
}

export function setDarkMode(mode){
    return Cookies.set(darkMode, mode, {expires: 365 })
}

export function getDarkMode(){
    return !(undefined === Cookies.get(darkMode) || 'false' === Cookies.get(darkMode));
}