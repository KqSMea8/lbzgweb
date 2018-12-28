
var storage = {};

storage.setSession = function(key, value) {
  if (value) {
    sessionStorage.setItem(key, JSON.stringify(value))
  }
}
 
storage.getSession =  function (key) {
  return JSON.parse(sessionStorage.getItem(key))
} 

storage.setLocal =  function (key, value) {
  if (value) {
    localStorage.setItem(key, JSON.stringify(value))
  }
}

storage.getLocal =  function (key) {
  return JSON.parse(localStorage.getItem(key))
}

export default storage;