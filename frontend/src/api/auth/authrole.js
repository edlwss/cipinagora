export function authrole(to, from, next) {
    const token = sessionStorage.getItem('token')

    if (to.meta.public) return next()

    if (!token && to.path !== '/login') {
        return next('/login')
    }

    if (!token && to.path === '/login') {
        return next()
    }

    if (token) {
        try {
            const payload = JSON.parse(atob(token.split('.')[1]))
            const userRoles = payload.roles || []
            const allowedRoles = to.meta.roles || []

            if (allowedRoles.length && !allowedRoles.some(r => userRoles.includes(r))) {
                return next('/')
            }

            return next()
        } catch (e) {
            console.warn('Ошибка парсинга токена в authrole.js', e)
            return next('/login')
        }
    }

    next()
}
