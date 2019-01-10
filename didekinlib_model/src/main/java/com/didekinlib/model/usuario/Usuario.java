package com.didekinlib.model.usuario;

import com.didekinlib.BeanBuilder;
import com.didekinlib.model.tx.TxState;

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
public final class Usuario implements Comparable<Usuario>, Serializable, TxState {

    private final TxStateId uId;
    private final String userName;  //email of the user.
    private final String alias;
    private final String password;
    private final String tokenAuth;
    private LifeCycleEnum lyfeStateCycle;

    private Usuario(UsuarioBuilder builder)
    {
        uId = builder.uId;
        userName = builder.userName;
        alias = builder.alias;
        password = builder.password;
        gcmToken = builder.gcmToken;
        tokenAuth = builder.tokenAuth;
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

    public TxStateId getuId()
    {
        return uId;
    }

    public String getGcmToken()
    {
        return gcmToken;
    }

    public String getTokenAuth()
    {
        return tokenAuth;
    }

    @Override
    public LifeCycleEnum getLifeCycle()
    {
        return lyfeStateCycle;
    }

    @Override
    public TxStateId getTxStateId()
    {
        return uId;
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
        if (uId != null && usuario.uId != null) {
            return uId.equals(usuario.uId);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        if(uId != null){
            return uId.hashCode();
        }
        throw new UnsupportedOperationException(error_message_bean_building + this.getClass().getName());
    }

    @Override
    public int compareTo(Usuario o)
    {
        if (userName == null || o == null || o.getUserName() == null) {
            throw new UnsupportedOperationException(error_message_bean_building + this.getClass().getName());
        }

        return userName.compareToIgnoreCase(o.getUserName());
    }

    //    ========================== BUILDER ===============================

    @SuppressWarnings({"WeakerAccess", "unused"})
    public static class UsuarioBuilder implements BeanBuilder<Usuario> {

        //Parameters; all optional.
        private TxStateId uId;
        private String userName = null;  //email of the user.
        private String alias = null;
        private String password = null;
        private String gcmToken;
        private String tokenAuth;

        public UsuarioBuilder()
        {
        }

        public UsuarioBuilder uId(TxStateId uId)
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

        public UsuarioBuilder gcmToken(String gcmToken)
        {
            this.gcmToken = gcmToken;
            return this;
        }

        public UsuarioBuilder tokenAuth(String tokenAuthIn)
        {
            this.tokenAuth = tokenAuthIn;
            return this;
        }

        @Override
        public Usuario build()
        {
            Usuario usuario = new Usuario(this);

            if (usuario.uId == null && usuario.userName == null) {
                throw new IllegalStateException(error_message_bean_building + this.getClass().getName());
            }
            return usuario;
        }

        public UsuarioBuilder copyUsuario(Usuario usuario)
        {
            uId = usuario.uId;
            password = usuario.password;
            userName = usuario.userName;
            alias = usuario.alias;
            gcmToken = usuario.gcmToken;
            tokenAuth = usuario.tokenAuth;
            return this;
        }
    }

//    ============================= SERIALIZATION PROXY ==================================


    private static class InnerSerial implements Serializable {

        private static final long serialVersionUID = USUARIO.serial();

        private final TxStateId usuarioId;
        private final String userName;
        private final String userAlias;
        private final String password;
        private final String gcmToken;
        private final String tokenAuth;

        public InnerSerial(Usuario usuario)
        {
            usuarioId = usuario.uId;
            userName = usuario.userName;
            userAlias = usuario.alias;
            password = usuario.password;
            gcmToken = usuario.gcmToken;
            tokenAuth = usuario.tokenAuth;
        }

        private Object readResolve()
        {
            return new UsuarioBuilder()
                    .uId(usuarioId)
                    .userName(userName)
                    .alias(userAlias)
                    .password(password)
                    .gcmToken(gcmToken)
                    .tokenAuth(tokenAuth)
                    .build();
        }
    }
}
