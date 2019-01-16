package com.didekinlib.model.usuario;

import com.didekinlib.BeanBuilder;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import static com.didekinlib.BeanBuilder.error_message_bean_building;
import static com.didekinlib.model.usuario.UsuarioSerialNumber.USUARIO;

/**
 * User: pedro
 * Date: 29/03/15
 * Time: 12:02
 */
public final class Usuario implements Comparable<Usuario>, Serializable {

    private final long uId;
    private final String userName;  //email of the user.
    private final String alias;
    private final String password;
    private final String tokenAuth;

    private Usuario(UsuarioBuilder builder)
    {
        uId = builder.uId;
        userName = builder.userName;
        alias = builder.alias;
        password = builder.password;
        tokenAuth = builder.tokenAuth;
    }

    public long getuId()
    {
        return uId;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getAlias()
    {
        return alias;
    }

    public String getPassword()
    {
        return password;
    }

    public String getTokenAuth()
    {
        return tokenAuth;
    }

    // ............................ Equals and hashCode ..........................

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Usuario usuario = (Usuario) o;
        if (uId > 0 && usuario.getuId() > 0) {
            return uId == usuario.uId;
        }
        if (usuario.userName != null && userName != null) {
            return userName.equals(usuario.userName);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash;

        if (userName == null && uId <= 0L) {
            throw new UnsupportedOperationException(error_message_bean_building + this.getClass().getName());
        } else {
            if (uId > 0) {
                hash = ((int) (uId ^ (uId >>> 32))) * 31;
            } else {
                hash = userName.hashCode();
            }
        }
        return hash;
    }

    @Override
    public int compareTo(Usuario o)
    {
        if (userName == null || o == null || o.getUserName() == null) {
            throw new UnsupportedOperationException(error_message_bean_building + this.getClass().getName());
        }
        return userName.compareToIgnoreCase(o.getUserName());
    }

    // ............................ Serializable ...............................

    /**
     * Return an InnerSerial object that will replace the current Usuario object during serialization.
     * In the deserialization the readResolve() method of the InnerSerial object will be used.
     */
    private Object writeReplace()
    {
        return new InnerSerial(this);
    }

    private void readObject(ObjectInputStream inputStream) throws InvalidObjectException
    {
        throw new InvalidObjectException("Use innerSerial to serialize");
    }

    //    ========================== BUILDER ===============================

    public static class UsuarioBuilder implements BeanBuilder<Usuario> {

        //Parameters; all optional.
        private long uId = 0L;
        private String userName = null;  //email of the user.
        private String alias = null;
        private String password = null;
        private String tokenAuth;

        public UsuarioBuilder()
        {
        }

        public UsuarioBuilder uId(long uId)
        {
            this.uId = uId;
            return this;
        }

        public UsuarioBuilder userName(String userName)
        {
            this.userName = userName;
            return this;
        }

        public UsuarioBuilder alias(String alias)
        {
            this.alias = alias;
            return this;
        }

        public UsuarioBuilder password(String password)
        {
            this.password = password;
            return this;
        }

        public UsuarioBuilder tokenAuth(String tokenAuthIn)
        {
            this.tokenAuth = tokenAuthIn;
            return this;
        }

        public UsuarioBuilder copyUsuario(Usuario usuario)
        {
            uId = usuario.uId;
            password = usuario.password;
            userName = usuario.userName;
            alias = usuario.alias;
            tokenAuth = usuario.tokenAuth;
            return this;
        }

        @Override
        public Usuario build()
        {
            Usuario usuario = new Usuario(this);

            if (usuario.uId == 0 && usuario.userName == null) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return usuario;
        }
    }

//    ============================= SERIALIZATION PROXY ==================================


    private static class InnerSerial implements Serializable {

        private static final long serialVersionUID = USUARIO.serial();

        private final long usuarioId;
        private final String userName;
        private final String userAlias;
        private final String password;
        private final String tokenAuth;

        public InnerSerial(Usuario usuario)
        {
            usuarioId = usuario.uId;
            userName = usuario.userName;
            userAlias = usuario.alias;
            password = usuario.password;
            tokenAuth = usuario.tokenAuth;
        }

        private Object readResolve()
        {
            return new UsuarioBuilder()
                    .uId(usuarioId)
                    .userName(userName)
                    .alias(userAlias)
                    .password(password)
                    .tokenAuth(tokenAuth)
                    .build();
        }
    }
}
